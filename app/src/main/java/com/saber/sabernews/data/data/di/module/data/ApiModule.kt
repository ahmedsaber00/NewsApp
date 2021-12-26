package com.saber.sabernews.data.data.di.module.data

import com.saber.sabernews.data.remote.base.factory.RemoteFactory.provideRetrofit
import com.saber.sabernews.data.remote.feature.news.service.NewsService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideNewsService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): NewsService = provideRetrofit(okHttpClient, moshi)
        .create(NewsService::class.java)

}