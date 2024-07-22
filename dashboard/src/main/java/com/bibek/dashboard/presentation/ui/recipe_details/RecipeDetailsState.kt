package com.bibek.dashboard.presentation.ui.recipe_details

import com.bibek.dashboard.data.remote.model.recipe_details.ExtendedIngredient

data class RecipeDetailsState(
    val title: String = "",
    val image: String = "",
    val ingredients: List<ExtendedIngredient> = listOf(),
    val isRecipeDetailsLoading : Boolean = true,
)
