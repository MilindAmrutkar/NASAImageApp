package com.backtocoding.nasaimageapp.data.repository

import com.backtocoding.nasaimageapp.core.network.ApiError
import com.backtocoding.nasaimageapp.core.network.ApiResponse
import com.backtocoding.nasaimageapp.data.local.NasaImageDao
import com.backtocoding.nasaimageapp.data.local.toDto
import com.backtocoding.nasaimageapp.data.remote.ApiService
import com.backtocoding.nasaimageapp.data.remote.NasaImageDto
import com.backtocoding.nasaimageapp.data.remote.toEntity
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val apiService: ApiService,
    private val nasaImageDao: NasaImageDao,
    private val moshi: Moshi
) {
    fun fetchImageOfTheDay(date: String? = null): Flow<ApiResponse<NasaImageDto>> = flow {
        emit(ApiResponse.Loading)
        try {
            // Try to fetch from local database first
            val localData = date?.let { nasaImageDao.getImageByDate(it).first() }
            localData?.let {
                emit(ApiResponse.Success(it.toDto()))
                return@flow
            }

            // If not found locally, fetch from the API
            val response = apiService.fetchImageOfTheDay(date)

            // Save to local database
            nasaImageDao.insertImage(response.toEntity())

            //Emit the received data
            emit(ApiResponse.Success(response))

        } catch (e: HttpException) {
            val apiError = try {
                val adapter = moshi.adapter(ApiError::class.java)
                adapter.fromJson(e.response()?.errorBody()?.string() ?: "")
            } catch (parseException: Exception) {
                null
            }
            emit(ApiResponse.Error(e, apiError))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e))
        }
    }
}