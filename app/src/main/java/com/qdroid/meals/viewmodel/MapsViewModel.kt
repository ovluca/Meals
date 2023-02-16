@file:Suppress("UNCHECKED_CAST")

package com.qdroid.meals.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qdroid.fashiondays.data.ApiResult
import com.qdroid.meals.data.Category
import com.qdroid.meals.data.Repository
import com.qdroid.meals.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Loading(true))
    internal val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadMeals(category: Category) {
        viewModelScope.launch {
            repository.getMealsByCategory(category).collect {
                when (it) {
                    is ApiResult.Error -> {}
                    is ApiResult.Loading -> _uiState.value =
                        UiState.Loading(it.data as Boolean)
                    is ApiResult.Success -> {
                        _uiState.value = UiState.Success(it.data as List<Category>)
                    }
                }
            }
        }
    }
}
