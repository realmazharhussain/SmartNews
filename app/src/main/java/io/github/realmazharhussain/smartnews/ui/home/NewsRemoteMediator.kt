package io.github.realmazharhussain.smartnews.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.realmazharhussain.smartnews.data.database.repository.NewsRepositoryLocal
import io.github.realmazharhussain.smartnews.data.database.entity.ArticleCache
import io.github.realmazharhussain.smartnews.data.database.entity.toEntity
import io.github.realmazharhussain.smartnews.data.network.dto.NewsRsp
import io.github.realmazharhussain.smartnews.data.network.repository.NewsRepositoryRemote
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val query: String,
    private val remoteRepository: NewsRepositoryRemote,
    private val localRepository: NewsRepositoryLocal
) : RemoteMediator<Int, ArticleCache>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleCache>
    ): MediatorResult {
        try {
            val loadedArticles = localRepository.count()
            val loadedPages = loadedArticles / state.config.pageSize

            val pageToLoad = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.APPEND -> loadedPages + 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            }

            when (val response = remoteRepository.everything(query, pageToLoad)) {
                is NewsRsp.Error -> throw response
                is NewsRsp.Ok -> {
                    if (loadType == LoadType.REFRESH) {
                        localRepository.removeAll()
                    }

                    localRepository.add(response.articles.map { it.toEntity() })
                    return MediatorResult.Success(endOfPaginationReached = loadedArticles >= response.totalResults)
                }
            }
        } catch (throwable: Throwable) {
            return if (throwable is HttpException && throwable.code() == 426) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(throwable)
            }
        }
    }

    override suspend fun initialize() = InitializeAction.SKIP_INITIAL_REFRESH
}
