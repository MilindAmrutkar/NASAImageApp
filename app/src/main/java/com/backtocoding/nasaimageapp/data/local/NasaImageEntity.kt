package com.backtocoding.nasaimageapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.backtocoding.nasaimageapp.data.remote.NasaImageDto

@Entity(tableName = "nasa_images")
data class NasaImageEntity(
    @PrimaryKey
    val date: String,
    val title: String?,
    val explanation: String?,
    val url: String?,
    val mediaType: String?
)

fun NasaImageEntity.toDto(): NasaImageDto {
    return NasaImageDto(
        date = this.date,
        title = this.title,
        explanation = this.explanation,
        url = this.url,
        mediaType = this.mediaType
    )
}


