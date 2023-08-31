package com.backtocoding.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String): Response<NasaImageResponse>
}