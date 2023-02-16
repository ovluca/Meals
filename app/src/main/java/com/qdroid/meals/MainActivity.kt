package com.qdroid.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import com.qdroid.meals.ui.screen.MapsScreen
import com.qdroid.meals.ui.screen.MenuScreen
import com.qdroid.meals.ui.screen.PopUpMarker
import com.qdroid.meals.ui.theme.MealsTheme
import com.qdroid.meals.viewmodel.MapsViewModel
import com.qdroid.meals.viewmodel.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val menuViewModel: MenuViewModel by viewModels()
    private val mapsViewModel: MapsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(title = { Text(text = "Title!") }, navigationIcon = {
                                IconButton(onClick = {
                                    coroutineScope.launch {
                                        scaffoldState.drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Menu, contentDescription = null
                                    )
                                }
                            })
                        },
                        drawerContent = {
                            MenuScreen(viewModel = menuViewModel, onMenuClick = {
                                coroutineScope.launch { scaffoldState.drawerState.close() }
                                mapsViewModel.loadMeals(it)
                            })
                        },
                        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    ) { contentPadding ->
                        MapsScreen(
                            viewModel = mapsViewModel,
                            contentPadding = contentPadding,
                            onMarkerClicked = { })
                    }
                }
            }
        }
    }
}

