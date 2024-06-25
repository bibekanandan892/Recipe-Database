package com.bibek.dashboard.data.repository

import com.bibek.core.utils.NetworkResult
import com.bibek.core.utils.handleResponse
import com.bibek.dashboard.data.model.scarch.res.RecipeSearchResponse
import com.bibek.dashboard.domain.repository.RecipeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl (private val httpClient: HttpClient) : RecipeRepository  {
    override fun getRecipe(
        query: String,
        cuisine: String,
        diet: String,
        sort: String
    ): Flow<NetworkResult<RecipeSearchResponse>> {
        return handleResponse {
            httpClient.get(urlString = "https://api.spoonacular.com/recipes/complexSearch"){
                parameter("query", query)
                parameter("cuisine", cuisine)
                parameter("diet", diet)
                parameter("sort", sort)
            }
        }
    }

}