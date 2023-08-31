package com.backtocoding.data.repository

import com.backtocoding.core.Result
import com.backtocoding.data.remote.NasaImageRemoteDataSource
import com.backtocoding.data.remote.NasaImageResponse
import javax.inject.Inject

class NasaImageRepository @Inject constructor(
    private val remoteDataSource: NasaImageRemoteDataSource
) {

    suspend fun fetchImageOfTheDay(apiKey: String): Result<NasaImageResponse> {
        return remoteDataSource.fetchImageOfTheDay(apiKey)
    }
}