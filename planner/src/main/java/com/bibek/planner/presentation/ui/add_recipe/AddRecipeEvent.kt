package com.bibek.planner.presentation.ui.add_recipe

sealed interface AddRecipeEvent {
    data class GetRecipe(val recipeId: String) :
        AddRecipeEvent
    data object NavigateBack : AddRecipeEvent
}