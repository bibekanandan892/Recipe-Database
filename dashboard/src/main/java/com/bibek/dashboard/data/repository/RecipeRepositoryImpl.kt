package com.bibek.dashboard.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.mapper.toRecipe
import com.bibek.dashboard.data.remote.RecipeRemoteMediator
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.domain.repository.RecipeRepository
import io.ktor.client.HttpClient
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
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 1),
            remoteMediator = RecipeRemoteMediator(
                recipeDao = recipeDao,
                httpClient = httpClient
            ),
            pagingSourceFactory = { recipeDao.getRecipePagingSource() }
        ).flow.map { pagingData-> pagingData.map { it.toRecipe()} }
    }
}