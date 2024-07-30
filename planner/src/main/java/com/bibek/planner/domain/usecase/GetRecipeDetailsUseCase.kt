package com.bibek.planner.domain.usecase

import com.bibek.core.utils.network.NetworkResult
import com.bibek.planner.data.model.recipe_details.RecipeDetailsDto
import com.bibek.planner.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    operator fun invoke(recipeId : String): Flow<NetworkResult<RecipeDetailsDto>> {
        return recipeRepository.getRecipeDetails(recipeId)
    }
}