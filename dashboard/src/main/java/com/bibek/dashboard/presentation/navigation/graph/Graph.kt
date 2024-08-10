package com.bibek.dashboard.presentation.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.bibek.core.utils.DEEP_LINK_BASE_URI
import com.bibek.core.utils.RECIPE_ID
import com.bibek.core.utils.navigation.Destination
import com.bibek.dashboard.presentation.ui.home.HomeScreen
import com.bibek.dashboard.presentation.ui.home.HomeViewModel
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsEvent
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsScreen
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsViewModel

fun NavGraphBuilder.dashboardGraph() {
    composable(route = Destination.HOME.name) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val uiState by homeViewModel.uiState.collectAsState()
        HomeScreen(uiState = uiState, homeViewModel::onEvent)
    }
    composable(
        route = Destination.RECIPE_DETAILS.name + "/{$RECIPE_ID}",
        arguments = listOf(navArgument(RECIPE_ID) { type = NavType.StringType }),
        deepLinks = listOf(navDeepLink {
            uriPattern = "$DEEP_LINK_BASE_URI${Destination.RECIPE_DETAILS.name}/{$RECIPE_ID}"
        })
    ) { backStackEntry ->
        val recipeId = backStackEntry.arguments?.getString(RECIPE_ID).toString()
        val recipeDetailsViewModel: RecipeDetailsViewModel = hiltViewModel()
        val uiState by recipeDetailsViewModel.uiState.collectAsState()
        LaunchedEffect(key1 = true) {
            recipeDetailsViewModel.onEvent(RecipeDetailsEvent.GetRecipe(recipeId = recipeId))
        }
        RecipeDetailsScreen(uiState = uiState, onEvent = recipeDetailsViewModel::onEvent)
    }

}



