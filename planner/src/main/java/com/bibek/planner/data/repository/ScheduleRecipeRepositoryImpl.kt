package com.bibek.planner.data.repository

import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.planner.data.mapper.toDomain
import com.bibek.planner.domain.model.RecipeAlarm
import com.bibek.planner.domain.repository.ScheduleRecipeRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScheduleRecipeRepositoryImpl(
    private val httpClient: HttpClient,
    private val recipeAlarmDao: RecipeAlarmDao,
) : ScheduleRecipeRepository {
    override fun getRecipeAlarmList(

    ): Flow<List<RecipeAlarm>> {
        return recipeAlarmDao.getAllRecipeAlarm().map { it.map{item -> item.toDomain()} }
    }

    override suspend fun getRecipeItem(recipeId: String): RecipeAlarm? {
        return recipeAlarmDao.getRecipeAlarmByRecipeId(recipeId = recipeId)?.toDomain()
    }
}