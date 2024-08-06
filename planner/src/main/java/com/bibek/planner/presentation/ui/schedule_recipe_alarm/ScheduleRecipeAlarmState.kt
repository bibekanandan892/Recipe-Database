package com.bibek.planner.presentation.ui.schedule_recipe_alarm

import com.bibek.core.utils.Day
import com.bibek.planner.domain.model.RecipeAlarm

data class ScheduleRecipeAlarmState(
    val selectedDay : Day= Day.SUN,
    val recipeAlarmList : List<RecipeAlarm> = listOf(),
)
