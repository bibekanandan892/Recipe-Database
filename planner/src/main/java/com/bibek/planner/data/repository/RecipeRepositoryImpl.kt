package com.bibek.planner.data.repository

import com.bibek.core.utils.network.NetworkResult
import com.bibek.core.utils.network.handleResponse
import com.bibek.planner.BuildConfig
import com.bibek.planner.data.model.recipe_details.RecipeDetailsDto
import com.bibek.planner.domain.repository.RecipeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val httpClient: HttpClient,
) : RecipeRepository {
    override fun getRecipeDetails(
        recipeId : String
    ): Flow<NetworkResult<RecipeDetailsDto>> {
        return handleResponse {
            httpClient.get(urlString = BuildConfig.GET_RECIPE + recipeId +"/information")
        }
    }
}