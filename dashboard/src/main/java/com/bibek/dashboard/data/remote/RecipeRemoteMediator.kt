package com.bibek.dashboard.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bibek.core.utils.NetworkResult
import com.bibek.core.utils.collectResponse
import com.bibek.core.utils.handleResponse
import com.bibek.dashboard.BuildConfig
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.data.model.scarch.res.RecipeSearchResponse
import com.bibek.dashboard.utils.Constants.PAGE_SIZE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import org.apache.http.HttpException
import java.io.IOException
import kotlin.jvm.functions.FunctionN

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    private val recipeDao: RecipeDao,
    private val httpClient: HttpClient
) : RemoteMediator<Int, Recipe>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Recipe>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastIndex = state.anchorPosition
                    lastIndex?.plus(PAGE_SIZE)
                }
            }
            val deferredResult = CompletableDeferred<MediatorResult>()

            handleResponse<RecipeSearchResponse> {
                httpClient.get(urlString = BuildConfig.SEARCH_URL) {
                    parameter("number", PAGE_SIZE)
                    parameter("offset", loadKey)
                }
            }.collectResponse(
                onSuccess = { response ->
                    val recipeList = response?.recipes?.filterNotNull() ?: listOf()
                    recipeDao.refreshRecipes(loadType, recipeList)
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