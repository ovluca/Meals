package com.qdroid.meals.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsResponse(
    @Json(name = "meals") val meals: List<Meal>,
)