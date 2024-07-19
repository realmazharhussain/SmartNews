package io.github.realmazharhussain.smartnews.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.realmazharhussain.smartnews.network.dto.Article
import io.github.realmazharhussain.smartnews.network.dto.NewsRsp
import io.github.realmazharhussain.smartnews.network.repository.NewsRepository

class NewsPagingSource(
    private val repository: NewsRepository,
    private val query: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>) = try {
        val key = params.key ?: 1
        when (val response = repository.everything(query, page = key)) {
            is NewsRsp.Error -> throw response
            is NewsRsp.Ok -> LoadResult.Page(
                data = response.articles,
                prevKey = if (key > 1) key - 1 else null,
                nextKey = if (key * repository.pageSize < response.totalResults) key + 1 else null
            )
        }
    } catch (t: Throwable) {
        LoadResult.Error(t)
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
