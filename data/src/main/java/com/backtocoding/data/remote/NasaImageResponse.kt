package com.backtocoding.data.remote

import com.squareup.moshi.Json

data class NasaImageResponse(
    @field:Json(name = "url")
    val ur: String,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "date")
    val date: String,

    @field:Json(name = "explanation")
    val description: String,

    @field:Json(name = "media_type")
    val mediaType: String
)
