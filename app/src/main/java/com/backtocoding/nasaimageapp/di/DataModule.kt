package com.backtocoding.nasaimageapp.di

import com.backtocoding.data.remote.NasaImageRemoteDataSource
import com.backtocoding.data.repository.NasaImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNasaImageRepository(
        remoteDataSource: NasaImageRemoteDataSource
    ): NasaImageRepository {
        return NasaImageRepository(remoteDataSource)
    }
}