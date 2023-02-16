package com.qdroid.meals.ui.state

sealed class UiState {
    data class Loading(val isLoading: Boolean = true) : UiState()
    data class Success(val listOfProducts: List<Any> = listOf()) :
        UiState()
}
