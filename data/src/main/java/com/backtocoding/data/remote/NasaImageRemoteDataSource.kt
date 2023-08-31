package com.backtocoding.data.remote

import com.backtocoding.core.Result
import javax.inject.Inject

class NasaImageRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchImageOfTheDay(apiKey: String): Result<NasaImageResponse> {
        return try {
            val response = apiService.getImageOfTheDay(apiKey)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                } ?: Result.Error("Data is null")
            } else {
                Result.Error("Failed to fetch data: ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error("Failed to fetch data: ${e.message}")
        }
    }
}