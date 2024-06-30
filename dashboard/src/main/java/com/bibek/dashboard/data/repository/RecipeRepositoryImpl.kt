package com.bibek.dashboard.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bibek.core.utils.NetworkResult
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.core.utils.handleResponse
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.domain.repository.RecipeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import com.bibek.dashboard.data.remote.RecipeRemoteMediator

 class   RecipeRepositoryImpl(
    private val httpClient: HttpClient,
    private val recipeDao: RecipeDao
) : RecipeRepository {


     @OptIn(ExperimentalPagingApi::class)
    override fun getRecipe(
        query: String, cuisine: String, diet: String, sort: String
    ):   Flow<PagingData<Recipe>> {
         return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RecipeRemoteMediator
                (
                recipeDao = recipeDao,
                httpClient = httpClient
            ),
            pagingSourceFactory = {
                recipeDao.getRecipePagingSource()
            }
        ).flow





























    }

}