package com.backtocoding.nasaimageapp.data.repository

import com.backtocoding.nasaimageapp.core.network.ApiResponse
import com.backtocoding.nasaimageapp.data.remote.ApiService
import com.backtocoding.nasaimageapp.data.remote.NasaImageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NasaRepository @Inject constructor(private val apiService: ApiService) {
    fun fetchImageOfTheDay(date: String? = null): Flow<ApiResponse<NasaImageDto>> = flow {
        emit(ApiResponse.Loading)
        try {
            val response = apiService.fetchImageOfTheDay(date)
            emit(ApiResponse.Success(response))

        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }
}