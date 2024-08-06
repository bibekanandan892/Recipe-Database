package com.bibek.dashboard.presentation.ui.recipe_details

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.bibek.core.utils.Day
import com.bibek.dashboard.data.remote.model.recipe_details.ExtendedIngredient

@Immutable
data class RecipeDetailsState(
    val id : Int? = null,
    val name: String = "",
    val image: String = "",
    val hour: Int = 0,
    val minute: Int = 0,
    val selectedDay: Day? = null,
    val selectedTime : String = "",
    val imageBitmap : Bitmap? = null,
    val ingredients: List<ExtendedIngredient> = listOf(),
    val isRecipeDetailsLoading : Boolean = true,
    val isShowClock : Boolean= false
)
