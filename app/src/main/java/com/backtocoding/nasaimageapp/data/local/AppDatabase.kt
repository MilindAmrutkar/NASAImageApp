package com.backtocoding.nasaimageapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NasaImageEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nasaImageDao(): NasaImageDao
}