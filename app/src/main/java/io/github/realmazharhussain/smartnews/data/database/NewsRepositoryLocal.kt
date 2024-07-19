package io.github.realmazharhussain.smartnews.data.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleCacheDao
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleSourceDao
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleSource
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.data.network.dto.Source
import io.github.realmazharhussain.smartnews.extension.ifNullOrBlank
import javax.inject.Inject

class NewsRepositoryLocal @Inject constructor(
    private val articleCacheDao: ArticleCacheDao,
    private val articleSourceDao: ArticleSourceDao
) {

    private suspend fun addOrUpdateSource(source: Source): Long = with(articleSourceDao) {
        find(source.id, source.name)?.let {
            update(it.copy(id = source.id.ifNullOrBlank { it.id }, name = source.name.ifBlank { it.name }))
            return it.autoId
        } ?: insert(source.toEntity())
    }

    suspend fun add(article: Article) {
        val sourceId = addOrUpdateSource(article.source)
        articleCacheDao.add(article.toEntity(sourceId))
    }

    suspend fun remove(url: String) {
        articleCacheDao.remove(url)

        val sourceId = articleCacheDao.getSourceId(url)
        if (sourceId != null && !articleCacheDao.existForSource(sourceId)) {
            articleSourceDao.remove(sourceId)
        }
    }

    suspend fun get(url: String): Article? = articleCacheDao.find(url)?.let {
        it.toArticle(source = articleSourceDao.find(it.sourceId)?.toSource() ?: Source.Unknown)
    }

    fun pagingSource() = object : PagingSource<Int, Article>() {
        private val dbSource = articleCacheDao.pagingSource()
        private fun LoadResult.Page<Int, Article>.toEntityPage() = LoadResult.Page(data = data.map { it.toEntity(sourceId = 0) }, prevKey = prevKey, nextKey = nextKey)
        private suspend fun LoadResult.Page<Int, ArticleCache>.toArticlePage() = LoadResult.Page(data = data.map { it.toArticle() }, prevKey = prevKey, nextKey = nextKey)

        override fun getRefreshKey(state: PagingState<Int, Article>) = dbSource.getRefreshKey(
            PagingState(
                pages = state.pages.map { it.toEntityPage() },
                anchorPosition = state.anchorPosition,
                config = state.config,
                leadingPlaceholderCount = 0
            )
        )

        override suspend fun load(params: LoadParams<Int>) = when(val result = dbSource.load(params)) {
            is LoadResult.Page -> result.toArticlePage()
            is LoadResult.Error -> LoadResult.Error(result.throwable)
            is LoadResult.Invalid -> LoadResult.Invalid()
        }
    }

    private fun Article.toEntity(sourceId: Long) = ArticleCache(sourceId, author, title, description, url, urlToImage, publishedAt, content)
    private fun ArticleCache.toArticle(source: Source) = Article(source, author, title, description, url, urlToImage, publishedAt, content)

    private fun Source.toEntity() = ArticleSource(id, name)
    private fun ArticleSource.toSource() = Source(id, name)

    private suspend inline fun ArticleCache.sourceEntity() = articleSourceDao.find(sourceId)
    private suspend inline fun ArticleCache.source() = sourceEntity()?.toSource() ?: Source.Unknown
    private suspend inline fun ArticleCache.toArticle() = toArticle(source())
}
