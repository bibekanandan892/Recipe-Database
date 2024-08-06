package com.bibek.planner.presentation.ui.recipe_alarm_details_screen

sealed interface RecipeAlarmDetailsEvent {
    data class GetRecipeAlarm(val recipeId: String) : RecipeAlarmDetailsEvent
    data object NavigateBack : RecipeAlarmDetailsEvent
}
