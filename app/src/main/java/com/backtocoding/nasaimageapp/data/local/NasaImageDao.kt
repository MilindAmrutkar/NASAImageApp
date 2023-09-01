package com.backtocoding.nasaimageapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NasaImageDao {
    @Query("SELECT * FROM nasa_images WHERE date = :date")
    fun getImageByDate(date: String): Flow<NasaImageEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(nasaImage: NasaImageEntity)
}