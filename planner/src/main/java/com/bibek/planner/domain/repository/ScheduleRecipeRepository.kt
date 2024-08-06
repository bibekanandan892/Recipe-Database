package com.bibek.planner.domain.repository

import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.planner.domain.model.RecipeAlarm
import kotlinx.coroutines.flow.Flow

interface ScheduleRecipeRepository {
    fun getRecipeAlarmList(
    ):Flow<List<RecipeAlarm>>

    suspend fun getRecipeItem(recipeId :String):RecipeAlarm?
}






