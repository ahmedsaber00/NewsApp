package com.saber.sabernews.data.remote.feature.news.service

import com.saber.sabernews.data.remote.base.model.GenericResponse
import com.saber.sabernews.data.remote.feature.news.model.NewsResponseModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsService {

    @GET("everything")
    suspend fun getAllNews(@QueryMap params: Map<String, String>): GenericResponse<NewsResponseModel>

}