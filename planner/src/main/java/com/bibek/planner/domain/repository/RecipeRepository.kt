package com.bibek.planner.domain.repository

import com.bibek.core.utils.network.NetworkResult
import com.bibek.planner.data.model.recipe_details.RecipeDetailsDto
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipeDetails(
        recipeId : String
    ):Flow<NetworkResult<RecipeDetailsDto>>
}






