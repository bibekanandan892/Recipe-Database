package com.bibek.planner.presentation.ui.recipe_alarm_details_screen

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.bibek.planner.data.model.recipe_details.ExtendedIngredient

@Immutable
data class RecipeAlarmDetailsState(
    val recipeId : Int? = null,
    val name: String = "",
    val imageBitmap : Bitmap? = null,
    val ingredients: List<ExtendedIngredient> = listOf(),
)
