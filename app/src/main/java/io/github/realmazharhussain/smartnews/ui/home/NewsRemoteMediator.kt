package io.github.realmazharhussain.smartnews.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import io.github.realmazharhussain.smartnews.data.database.CacheDatabase
import io.github.realmazharhussain.smartnews.data.database.NewsRepositoryLocal
import io.github.realmazharhussain.smartnews.data.network.dto.Article
import io.github.realmazharhussain.smartnews.data.network.dto.NewsRsp
import io.github.realmazharhussain.smartnews.data.network.repository.NewsRepositoryRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val query: String,
    private val cacheDb: CacheDatabase,
    private val remoteRepository: NewsRepositoryRemote,
    private val localRepository: NewsRepositoryLocal
) : RemoteMediator<Int, Article>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        try {
            val pageNo = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> if (state.lastItemOrNull() == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                } else {
                    state.pages.size
                }
            }

            when (val response = remoteRepository.everything(query, pageNo)) {
                is NewsRsp.Error -> return MediatorResult.Error(response)
                is NewsRsp.Ok -> {
                    if (loadType == LoadType.REFRESH) withContext(Dispatchers.IO) {
                        cacheDb.clearAllTables()
                    }

                    response.articles.forEach { localRepository.add(it) }

                    return MediatorResult.Success(endOfPaginationReached = pageNo * remoteRepository.pageSize >= response.totalResults)
                }
            }
        } catch (t: Throwable) {
            return MediatorResult.Error(t)
        }
    }
}