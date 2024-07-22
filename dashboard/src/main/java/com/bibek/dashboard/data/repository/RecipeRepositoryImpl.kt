package com.bibek.dashboard.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bibek.core.utils.network.NetworkResult
import com.bibek.core.utils.network.handleResponse
import com.bibek.dashboard.BuildConfig
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.mapper.toRecipe
import com.bibek.dashboard.data.remote.RecipeRemoteMediator
import com.bibek.dashboard.data.remote.model.query.Query
import com.bibek.dashboard.data.remote.model.recipe_details.RecipeDetailsDto
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.domain.repository.RecipeRepository
import com.bibek.dashboard.utils.PAGE_SIZE
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val httpClient: HttpClient,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getRecipe(
        query: String, cuisine: String, diet: String, sort: String
    ): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 0,
                enablePlaceholders = true, initialLoadSize = PAGE_SIZE + (2 * 5)),
            remoteMediator = RecipeRemoteMediator(
                recipeDao = recipeDao,
                httpClient = httpClient,
                query = Query(
                    query = query,
                    cuisine = cuisine,
                    diet = diet,
                    sort =sort
                )

            ),
            pagingSourceFactory = { recipeDao.getRecipePagingSource() }
        ).flow.map { pagingData-> pagingData.map { it.toRecipe()} }
    }

    override fun getRecipeDetails(
        recipeId : String
    ): Flow<NetworkResult<RecipeDetailsDto>> {
        return handleResponse {
            httpClient.get(urlString = BuildConfig.GET_RECIPE + recipeId +"/information")
        }
    }
}