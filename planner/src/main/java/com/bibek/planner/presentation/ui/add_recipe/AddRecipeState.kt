package com.bibek.planner.presentation.ui.add_recipe

import com.bibek.planner.data.model.recipe_details.ExtendedIngredient

data class AddRecipeState(
    val title: String = "",
    val image: String = "",
    val ingredients: List<ExtendedIngredient> = listOf(),
    val isRecipeDetailsLoading : Boolean = true,
)
