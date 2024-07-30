package com.bibek.planner.presentation.navigatoin

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bibek.core.utils.navigation.Destination
import com.bibek.planner.presentation.ui.add_recipe.AddRecipeEvent
import com.bibek.planner.presentation.ui.add_recipe.AddRecipeScreen
import com.bibek.planner.presentation.ui.add_recipe.AddRecipeViewModel

fun NavGraphBuilder.recipeGraph() {
    composable(route = Destination.ADD_RECIPE.name + "/{recipeId}") { backStackEntry ->
        val recipeId = backStackEntry.arguments?.getString("recipeId").toString()
        val addRecipeViewModel: AddRecipeViewModel = hiltViewModel()
        val uiState by addRecipeViewModel.uiState.collectAsState()
        LaunchedEffect(key1 = true) {
            addRecipeViewModel.onEvent(AddRecipeEvent.GetRecipe(recipeId = recipeId))
        }
        AddRecipeScreen(uiState = uiState, addRecipeViewModel::onEvent)
    }
}
