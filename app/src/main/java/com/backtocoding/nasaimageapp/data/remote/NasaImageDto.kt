package com.backtocoding.nasaimageapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NasaImageDto(
    @Json(name = "url") val url: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "date") val date: String?,
    @Json(name = "explanation") val explanation: String?,
    @Json(name = "media_type") val mediaType: String?
)
