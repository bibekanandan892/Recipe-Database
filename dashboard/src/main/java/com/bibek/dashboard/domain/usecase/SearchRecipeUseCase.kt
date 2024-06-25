package com.bibek.dashboard.domain.usecase

import com.bibek.core.utils.NetworkResult
import com.bibek.dashboard.data.model.scarch.res.RecipeSearchResponse
import com.bibek.dashboard.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipeUseCase (private val recipeRepository: RecipeRepository) {
    operator fun invoke(query: String,
                        cuisine: String,
                        diet: String,
                        sort: String): Flow<NetworkResult<RecipeSearchResponse>> {
        return recipeRepository.getRecipe(query, cuisine, diet, sort)
    }
}