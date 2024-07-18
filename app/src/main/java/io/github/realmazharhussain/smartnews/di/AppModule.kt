package io.github.realmazharhussain.smartnews.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.realmazharhussain.smartnews.network.dto.NewsRspJsonAdapter
import io.github.realmazharhussain.smartnews.network.service.NewsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides fun providesNewsService(): NewsService = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(NewsRspJsonAdapter()).build()
            )
        )
        .build().create(NewsService::class.java)
}
