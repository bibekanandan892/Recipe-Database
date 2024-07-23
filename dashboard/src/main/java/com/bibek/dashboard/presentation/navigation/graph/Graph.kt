package com.bibek.dashboard.presentation.navigation.graph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bibek.core.utils.navigation.Destination
import com.bibek.dashboard.presentation.ui.home.HomeScreen
import com.bibek.dashboard.presentation.ui.home.HomeViewModel
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsScreen

fun NavGraphBuilder.dashboardGraph() {
    composable(route = Destination.HOME.name) {
        val homeViewModel : HomeViewModel = hiltViewModel()
        val uiState by homeViewModel.uiState.collectAsState()
        HomeScreen(uiState = uiState,homeViewModel::onEvent)
    }
    composable(
        route = Destination.RECIPE_DETAILS.name
    ) {
        RecipeDetailsScreen()
    }
}



