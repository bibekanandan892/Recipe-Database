package com.bibek.dashboard.presentation.ui.recipe_details

import android.graphics.Bitmap
import com.bibek.core.utils.Day

sealed interface RecipeDetailsEvent {
    data object OnAddRecipeClick : RecipeDetailsEvent
    data object OnViewMore : RecipeDetailsEvent
    data class GetRecipe(val recipeId: String) : RecipeDetailsEvent
    data object NavigateBack : RecipeDetailsEvent
    data class SetRecipeAlarm(val image :Bitmap,val alarmId : Int) : RecipeDetailsEvent
    data object OnTimeClick : RecipeDetailsEvent
    data object OnCloseClock : RecipeDetailsEvent
    data class OnTimeSelect(val hour : Int, val minutes : Int) : RecipeDetailsEvent
    data class OnDaySelect(val day : Day) : RecipeDetailsEvent
}
