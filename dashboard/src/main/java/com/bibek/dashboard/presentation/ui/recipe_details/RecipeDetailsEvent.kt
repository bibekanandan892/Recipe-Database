package com.bibek.dashboard.presentation.ui.recipe_details

import com.bibek.dashboard.presentation.ui.components.Day

sealed interface RecipeDetailsEvent {
    data object OnAddRecipeClick : RecipeDetailsEvent
    data object OnViewMore : RecipeDetailsEvent
    data class GetRecipe(val recipeId: String) : RecipeDetailsEvent
    data object NavigateBack : RecipeDetailsEvent
    data object SetRecipeAlarm : RecipeDetailsEvent
    data object OnTimeClick : RecipeDetailsEvent
    data object OnCloseClock : RecipeDetailsEvent
    data class OnTimeSelect(val hour : Int, val minutes : Int) : RecipeDetailsEvent
    data class OnDaySelect(val day : Day) : RecipeDetailsEvent
}
