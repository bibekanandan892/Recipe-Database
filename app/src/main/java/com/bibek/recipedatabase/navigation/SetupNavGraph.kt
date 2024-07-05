package com.bibek.recipedatabase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bibek.dashboard.presentation.navigation.graph.dashboardGraph

@Composable
fun SetupNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        dashboardGraph()
    }
}

