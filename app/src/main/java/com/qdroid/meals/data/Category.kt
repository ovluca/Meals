package com.qdroid.meals.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "idCategory") val id: String,
    @Json(name = "strCategory") val name: String,
    @Json(name = "strCategoryThumb") val thumbnail: String,
    @Json(name = "strCategoryDescription") val description: String,
)