package com.saber.sabernews.data.data.di.module.data

import com.saber.sabernews.data.data.feature.news.NewsDataRepository
import com.saber.sabernews.domain.feature.news.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNewsRepository(dataRepository: NewsDataRepository): NewsRepository

}