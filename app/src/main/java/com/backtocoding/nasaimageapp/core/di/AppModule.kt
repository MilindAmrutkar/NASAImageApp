package com.backtocoding.nasaimageapp.core.di

import android.content.Context
import androidx.room.Room
import com.backtocoding.nasaimageapp.BuildConfig
import com.backtocoding.nasaimageapp.core.utils.Constants
import com.backtocoding.nasaimageapp.data.local.AppDatabase
import com.backtocoding.nasaimageapp.data.local.NasaImageDao
import com.backtocoding.nasaimageapp.data.remote.ApiService
import com.backtocoding.nasaimageapp.data.repository.NasaRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()
                val newRequest = request.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(loggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNasaImageRepository(
        apiService: ApiService,
        nasaImageDao: NasaImageDao,
        moshi: Moshi
    ): NasaRepository {
        return NasaRepository(apiService, nasaImageDao, moshi)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "nasa_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNasaImageDao(db: AppDatabase): NasaImageDao {
        return db.nasaImageDao()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}