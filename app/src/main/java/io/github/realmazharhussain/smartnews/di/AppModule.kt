package io.github.realmazharhussain.smartnews.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.realmazharhussain.smartnews.data.database.CacheDatabase
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleCacheDao
import io.github.realmazharhussain.smartnews.data.database.dao.ArticleSourceDao
import io.github.realmazharhussain.smartnews.data.network.dto.NewsRspJsonAdapter
import io.github.realmazharhussain.smartnews.data.network.service.NewsService
import io.github.realmazharhussain.smartnews.data.network.service.SummaryService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides fun providesNewsService(): NewsService = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(NewsRspJsonAdapter()).build()
            )
        )
        .build().create(NewsService::class.java)

    @Provides fun providesSummaryService(): SummaryService = Retrofit.Builder()
        .baseUrl("https://api.ai21.com/studio/v1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(SummaryService::class.java)

    @Provides fun providesCacheDatabase(@ApplicationContext context: Context): CacheDatabase =
        Room.databaseBuilder(context, CacheDatabase::class.java, "cache_database").build()

    @Provides fun providesArticleCacheDao(db: CacheDatabase): ArticleCacheDao = db.articleCacheDao()
    @Provides fun providesArticleSourceDao(db: CacheDatabase): ArticleSourceDao = db.articleSourceDao()
}
