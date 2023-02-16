package com.qdroid.meals.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategoryName(@Query("c") categoryName: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealsDetails(@Query("i") mealId: Int): MealsResponse
}