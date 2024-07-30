package com.bibek.dashboard.domain.repository

import androidx.paging.PagingData
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.core.utils.network.NetworkResult
import com.bibek.dashboard.data.remote.model.recipe_details.RecipeDetailsDto
import com.bibek.dashboard.domain.model.search.response.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipe(
        query: String,
        cuisine: String,
        diet: String,
        sort: String
    ): Flow<PagingData<Recipe>>
    fun getRecipeDetails(
        recipeId : String
    ):Flow<NetworkResult<RecipeDetailsDto>>
    suspend fun saveRecipeAlarm(recipeAlarmEntity: RecipeAlarmEntity)
}






