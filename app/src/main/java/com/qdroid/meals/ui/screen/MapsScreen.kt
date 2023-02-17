package com.qdroid.meals.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.qdroid.meals.data.Meal
import com.qdroid.meals.ui.state.UiState
import com.qdroid.meals.utils.LocationExt
import com.qdroid.meals.viewmodel.MapsViewModel

@Composable
fun MapsScreen(
    viewModel: MapsViewModel,
    contentPadding: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()
    var listOfProducts = listOf<Meal>()
    if (uiState is UiState.Success) {
        listOfProducts = (uiState as UiState.Success).listOfProducts as List<Meal>
    }
    val london = LatLng(51.5285582, -0.2416812)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(london, 10f)
    }
    GoogleMap(
        contentPadding = contentPadding,
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        for (meal in listOfProducts) {
            val position = LocationExt().getLocation(defaultLocation = london)
            val infoWindowState = rememberMarkerState(position = position)
            MarkerInfoWindow(state = infoWindowState) {
                Card {
                    Column {
                        Text(text = meal.name, fontWeight = FontWeight.Bold)
                        Text(
                            text = meal.details ?: "",
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp
                        )
                    }
                }

            }
            Marker(
                state = MarkerState(position = position),
                title = meal.name,
                snippet = meal.details
            )
        }
    }
}