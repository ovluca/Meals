package com.qdroid.meals.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meal(
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val thumbnail: String?,
    @Json(name = "idMeal") val id: String?,
    @Json(name = "strInstructions") var details: String?,
)