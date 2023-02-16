package com.qdroid.meals.ui.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup

@Composable
fun PopUpMarker() {
    Popup(
        alignment = Alignment.CenterStart,
        offset = IntOffset(0, 700),
    ) {
       Text(text = "FSDFDSGDSAGDGADSF")
    }
}