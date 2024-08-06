package com.bibek.planner.domain.model

import android.graphics.Bitmap
import com.bibek.planner.data.model.recipe_details.ExtendedIngredient

data class RecipeAlarm(
    val alarmId: Int? = 0,
    val time :String= "",
    val name: String = "",
    val image: Bitmap? = null,
    val recipeId : String = "",
    val dayOfWeek: Int? = null,
    val ingredients: List<ExtendedIngredient> = listOf(),
)