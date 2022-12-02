package com.example.moodapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "name") var name: String,
    @Json(name = "genre")var genre: String,
    @Json(name = "cover")var cover: String,)