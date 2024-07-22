package com.bibek.dashboard.presentation.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bibek.core.utils.navigation.Destination
import com.bibek.dashboard.presentation.ui.home.HomeScreen
import com.bibek.dashboard.presentation.ui.home.HomeViewModel
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsEvent
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsScreen
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsViewModel

fun NavGraphBuilder.dashboardGraph() {
    composable(route = Destination.HOME.name) {
        val homeViewModel : HomeViewModel = hiltViewModel()
        val uiState by homeViewModel.uiState.collectAsState()
        HomeScreen(uiState = uiState,homeViewModel::onEvent)
    }
    composable(
        route = Destination.RECIPE_DETAILS.name+"/{recipeId}"
    ) { backStackEntry->
        val recipeId = backStackEntry.arguments?.getString("recipeId").toString()
        val recipeDetailsViewModel : RecipeDetailsViewModel = hiltViewModel()
        val uiState by recipeDetailsViewModel.uiState.collectAsState()
        LaunchedEffect(key1 = true) {
            recipeDetailsViewModel.onEvent(RecipeDetailsEvent.GetRecipe(recipeId = recipeId))
        }
        RecipeDetailsScreen(uiState = uiState, onEvent = recipeDetailsViewModel::onEvent)
    }
}



