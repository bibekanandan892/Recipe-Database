package com.bibek.dashboard.presentation.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.bibek.core.utils.navigation.Destination
import com.bibek.dashboard.presentation.ui.home.HomeScreen
import com.bibek.dashboard.presentation.ui.home.HomeViewModel
import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsScreen

fun NavGraphBuilder.dashboardGraph() {
    composable(route = Destination.HOME.name) {
        val homeViewModel : HomeViewModel = hiltViewModel()
        val recipePager = homeViewModel.recipePager.collectAsLazyPagingItems()
        HomeScreen(recipePager,homeViewModel::onEvent)
    }
    composable(
        route = Destination.RECIPE_DETAILS.name
    ) {
        RecipeDetailsScreen()
    }
}



