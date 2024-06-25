package com.bibek.dashboard.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bibek.core.utils.Destination
import com.bibek.dashboard.presentation.ui.home.HomeScreen
import com.bibek.dashboard.presentation.ui.home.HomeViewModel
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsScreen

/**
 * Author: Bibekananda Nayak
 *
 * Date: 01-05-2024
 */
fun NavGraphBuilder.dashboardGraph(navController: NavHostController) {

    composable(route = Destination.Home.route) {

            val homeViewModel : HomeViewModel = hiltViewModel()
        val isShowSplash by homeViewModel.isShowSplash
        HomeScreen(homeViewModel.recipeResponse, isShowSplash = isShowSplash)
    }

    composable(
        route = Destination.RecipeDetails.route
    ) {
        RecipeDetailsScreen()
    }

}

@Composable
fun NavBackStackEntry.getRememberedParent(navController: NavHostController): NavBackStackEntry {
    val parentId = this.destination.parent!!.id
    return remember(this) { navController.getBackStackEntry(parentId) }
}



