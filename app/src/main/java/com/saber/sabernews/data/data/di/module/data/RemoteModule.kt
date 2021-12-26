package com.saber.sabernews.data.data.di.module.data

import com.saber.sabernews.data.data.feature.news.repository.NewsRemote
import com.saber.sabernews.data.data.feature.news.store.NewsRemoteDataStore
import com.saber.sabernews.extensions.getDate
import com.saber.sabernews.extensions.getOffsetDate
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    companion object {

        /**
         * The order of the interceptors in the OkHttpClient Builder is important.
         */
        @Provides
        @Singleton
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient =  OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()


        /**
         * //Ref. https://stackoverflow.com/a/44478162
         */
        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder()
            .add(DateAdapter())
            //if you have more adapters, add them before this line:
            .add(KotlinJsonAdapterFactory())
            .build()

        @Provides
        @Singleton
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }

    }

    @Binds
    abstract fun bindNewsRemote(remoteImpl: NewsRemoteDataStore): NewsRemote


}

class DateAdapter {
    @FromJson
    fun fromJson(json: String): Date? = json.getDate()

    @ToJson
    fun toJson(date: Date) = date.getOffsetDate()
}