package com.bibek.dashboard.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bibek.core.utils.PAGE_SIZE
import com.bibek.core.utils.connectivity.ConnectionState
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.core.utils.network.collectResponse
import com.bibek.core.utils.network.handleResponse
import com.bibek.dashboard.BuildConfig
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.local.model.search.RecipeEntity
import com.bibek.dashboard.data.mapper.toRecipeEntity
import com.bibek.dashboard.data.remote.model.query.Query
import com.bibek.dashboard.data.remote.model.search.response.RecipeSearchDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.first


class RecipeRemoteMediator(
    private val recipeDao: RecipeDao,
    private val httpClient: HttpClient,
    private val query: Query,
    private val connectivityObserver: ConnectivityObserver
) : PagingSource<Int, RecipeEntity>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeEntity> {
        return try {
            val prev = params.key ?: 0
            val adjustedLoadSize =
                params.loadSize.coerceAtMost(PAGE_SIZE) // Modify loadSize if needed
            val deferredResult = CompletableDeferred<LoadResult<Int, RecipeEntity>>()
            if (connectivityObserver.currentConnectionState == ConnectionState.Available) {
                handleResponse<RecipeSearchDto> {
                    httpClient.get(urlString = BuildConfig.SEARCH_URL) {
                        parameter("number", PAGE_SIZE)
                        parameter("offset", prev)
                        parameter("query", query.query)
                        parameter("cuisine", query.cuisine)
                        parameter("sort", query.sort)
                        parameter("diet", query.diet)
                    }
                }.collectResponse(
                    onSuccess = { response ->
                        val recipeList = response?.recipeDtos?.filterNotNull() ?: listOf()
                        if (prev == 0) {
                            recipeDao.refreshRecipes(recipeList.map { it.toRecipeEntity() })
                        }
                        deferredResult.complete(
                            LoadResult.Page(
                                data = recipeList.map {
                                    Log.d("getRecipe", "getRecipe: ${it.toRecipeEntity()}")
                                    it.toRecipeEntity() },
                                prevKey = if (prev <= 0) null else prev - PAGE_SIZE,
                                nextKey = if (recipeList.size < adjustedLoadSize) null else prev + PAGE_SIZE
                            )
                        )
                    }, onError = {
                        deferredResult.complete(
                            LoadResult.Page(
                                data = recipeDao.getAllRecipe().first(),
                                prevKey = null,
                                nextKey = null
                            )
                        )
                    }, onLoading = {})
            } else {
                deferredResult.complete(
                    LoadResult.Page(
                        data = recipeDao.getAllRecipe().first(),
                        prevKey = null,
                        nextKey = null
                    )
                )
            }
            return deferredResult.await()
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}