package com.example.moodapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCategory(
    @Json(name = "id") var id: Integer,
    @Json(name = "mood") var mood: String,
    @Json(name = "recommend") var recommendedList: List<Movie>,)
