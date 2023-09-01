package com.backtocoding.nasaimageapp.data.remote

import com.backtocoding.nasaimageapp.data.local.NasaImageEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NasaImageDto(
    @Json(name = "url") val url: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "date") val date: String,
    @Json(name = "explanation") val explanation: String?,
    @Json(name = "media_type") val mediaType: String?
)

fun NasaImageDto.toEntity(): NasaImageEntity {
    return NasaImageEntity(
        date = this.date,
        title = this.title,
        explanation = this.explanation,
        url = this.url,
        mediaType = this.mediaType
    )
}
