package com.backtocoding.nasaimageapp.core.network

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Exception, val apiError: ApiError? = null) :
        ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}