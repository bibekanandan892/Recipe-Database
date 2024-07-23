package com.bibek.dashboard.domain.usecase

import com.bibek.core.utils.network.NetworkResult
import com.bibek.dashboard.data.remote.model.recipe_details.RecipeDetailsDto
import com.bibek.dashboard.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    operator fun invoke(recipeId : String): Flow<NetworkResult<RecipeDetailsDto>> {
        return recipeRepository.getRecipeDetails(recipeId)
    }
}