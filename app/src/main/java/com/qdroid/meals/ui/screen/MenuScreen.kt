package com.qdroid.meals.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.qdroid.meals.data.Category
import com.qdroid.meals.ui.state.UiState
import com.qdroid.meals.viewmodel.MenuViewModel

@Composable
fun MenuScreen(onMenuClick: (category: Category) -> Unit, viewModel: MenuViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var listOfProducts = listOf<Category>()
    if (uiState is UiState.Success) {
        listOfProducts = (uiState as UiState.Success).listOfProducts as List<Category>
    }
    LazyColumn {
        items(listOfProducts) { category ->
            Text(
                modifier = Modifier
                    .clickable { onMenuClick(category) }
                    .padding(10.dp),
                text = category.name,
                fontWeight = FontWeight.Bold
            )
        }
    }
}