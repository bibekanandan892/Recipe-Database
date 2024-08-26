package com.bibek.planner.presentation.ui.schedule_recipe_alarm

import com.bibek.core.utils.Day

sealed interface ScheduleRecipeAlarmEvent {
    data class NavigateToRecipeAlarmDetails(val recipeId : String):ScheduleRecipeAlarmEvent
    data object NavigateBack : ScheduleRecipeAlarmEvent
    data class OnDaySelect(val day: Day) : ScheduleRecipeAlarmEvent

    data class DeleteAlarm(val alarmId : Int) : ScheduleRecipeAlarmEvent
}