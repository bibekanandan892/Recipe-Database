package com.bibek.dashboard.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bibek.dashboard.data.local.RecipeDatabase
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.data.model.scarch.res.RecipeSearchResponse
import com.bibek.dashboard.utils.Constants.PAGE_SIZE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.apache.http.HttpException
import java.io.IOException
import kotlin.jvm.functions.FunctionN

@OptIn(ExperimentalPagingApi::class)
class RecipeRemoteMediator(
    private val recipeDatabase: RecipeDatabase,
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

            val response =
                httpClient.get(urlString = "https://api.spoonacular.com/recipes/complexSearch") {
                    parameter("number", PAGE_SIZE)
                    parameter("offset", loadKey)
                }.body<RecipeSearchResponse>()

            val recipeList = response.recipes?.filterNotNull() ?: listOf()

            recipeDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    recipeDatabase.recipeDao().deleteAll()
                }
                recipeDatabase.recipeDao().upsertAll(recipeList)
            }
            MediatorResult.Success(
                endOfPaginationReached = recipeList.isEmpty() || response.offset == 900
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}