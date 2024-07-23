package com.bibek.dashboard.presentation.ui.recipe_details

sealed interface RecipeDetailsEvent {
    data object OnAddRecipeClick : RecipeDetailsEvent
    data object OnViewMore : RecipeDetailsEvent
    data class GetRecipe(val recipeId: String) : RecipeDetailsEvent
    object NavigateBack : RecipeDetailsEvent
}
