package com.bibek.dashboard.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bibek.core.utils.network.collectResponse
import com.bibek.core.utils.network.handleResponse
import com.bibek.dashboard.BuildConfig
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.local.model.search.RecipeEntity
import com.bibek.dashboard.data.mapper.toRecipeEntity
import com.bibek.dashboard.data.remote.model.query.Query
import com.bibek.dashboard.data.remote.model.search.response.RecipeSearchDto
import com.bibek.dashboard.utils.PAGE_SIZE
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CompletableDeferred

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    private val recipeDao: RecipeDao,
    private val httpClient: HttpClient,
    private val query: Query
) : RemoteMediator<Int, RecipeEntity>() {
    private var nextPage : Int = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RecipeEntity>
    ): MediatorResult {
        return try {
             nextPage = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND ->  nextPage.plus(PAGE_SIZE)
            }
            val deferredResult = CompletableDeferred<MediatorResult>()
            handleResponse<RecipeSearchDto> {
                httpClient.get(urlString = BuildConfig.SEARCH_URL) {
                    parameter("number", PAGE_SIZE)
                    parameter("offset", nextPage)
                    parameter("query", query.query)
                    parameter("cuisine", query.cuisine)
                    parameter("sort", query.sort)
                    parameter("diet", query.diet)
                }
            }.collectResponse(
                onSuccess = { response ->
                    val recipeList = response?.recipeDtos?.filterNotNull() ?: listOf()
                    recipeDao.refreshRecipes(loadType, recipeList.map { it.toRecipeEntity() })
                    deferredResult.complete(
                         MediatorResult.Success(
                            endOfPaginationReached = recipeList.isEmpty() || response?.offset == 900
                        )
                    )
                }, onError = {
                    deferredResult.complete(MediatorResult.Error(Exception(it)))
                }, onLoading = {})
            deferredResult.await()
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}