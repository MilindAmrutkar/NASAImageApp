package com.backtocoding.nasaimageapp.data.remote

import com.backtocoding.nasaimageapp.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("planetary/apod")
    suspend fun fetchImageOfTheDay(
        @Query("date") date: String? = null,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): NasaImageDto
}