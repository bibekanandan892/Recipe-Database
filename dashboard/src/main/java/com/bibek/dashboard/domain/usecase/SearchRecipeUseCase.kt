package com.bibek.dashboard.domain.usecase

import androidx.paging.PagingData
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    operator fun invoke(query: String,
                        cuisine: String,
                        diet: String,
                        sort: String): Flow<PagingData<Recipe>>  {
        return recipeRepository.getRecipe(query, cuisine, diet, sort)
    }
}