package com.qdroid.meals.data

import com.qdroid.fashiondays.data.ApiResult
import kotlinx.coroutines.flow.flow

class Repository(private val apiService: ApiService) {

    fun getCategories() = flow {
        emit(ApiResult.Loading(true))
        try {
            emit(ApiResult.Success(apiService.getCategories().categories))
        } catch (e: java.lang.Exception) {
            emit(ApiResult.Error(e.message.toString()))
        }
    }

    fun getMealsByCategory(category: Category) = flow {
        emit(ApiResult.Loading(true))
        try {
            val meals = apiService.getMealsByCategoryName(categoryName = category.name).meals
            for (meal in meals) {
                meal.id?.let {
                    val response = apiService.getMealsDetails(it.toInt()).meals[0]
                    meal.details = response.details
                }
            }
            emit(ApiResult.Success(meals))
        } catch (e: java.lang.Exception) {
            emit(ApiResult.Error(e.message.toString()))
        }
    }

    fun getMenu() = flow {
        emit(ApiResult.Loading(true))
        try {
            val menuList = mutableListOf<Menu>()
            for (category in apiService.getCategories().categories) {
                val mealsByCategoryResponse =
                    apiService.getMealsByCategoryName(categoryName = category.name)
                val menu = Menu(category, mealsByCategoryResponse.meals)
                menuList.add(menu)
            }
            emit(ApiResult.Success(menuList))
        } catch (e: java.lang.Exception) {
            emit(ApiResult.Error(e.message.toString()))
        }
    }
}