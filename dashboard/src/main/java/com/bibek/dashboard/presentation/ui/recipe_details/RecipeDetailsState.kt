package com.bibek.dashboard.presentation.ui.recipe_details

import androidx.compose.runtime.Immutable
import com.bibek.dashboard.data.remote.model.recipe_details.ExtendedIngredient
import com.bibek.dashboard.presentation.ui.components.Day

@Immutable
data class RecipeDetailsState(
    val id : Int? = null,
    val title: String = "",
    val image: String = "",
    val hour: Int = 0,
    val min: Int = 0,
    val selectedDay: Day? = null,
    val selectedTime : String = "",
    val ingredients: List<ExtendedIngredient> = listOf(),
    val isRecipeDetailsLoading : Boolean = true,
    val isShowClock : Boolean= false
)
