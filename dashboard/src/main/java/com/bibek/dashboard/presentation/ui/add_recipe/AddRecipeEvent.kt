package com.bibek.dashboard.presentation.ui.add_recipe

import com.bibek.dashboard.presentation.ui.recipe_details.RecipeDetailsEvent

sealed interface AddRecipeEvent {
    data class GetRecipe(val recipeId: String) :
        AddRecipeEvent
    data object NavigateBack : AddRecipeEvent
}