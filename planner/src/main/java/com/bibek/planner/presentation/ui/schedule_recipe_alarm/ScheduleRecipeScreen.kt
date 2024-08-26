package com.bibek.planner.presentation.ui.schedule_recipe_alarm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.bibek.core.ui.color.theme.ColorBackground
import com.bibek.core.ui.components.TopBar
import com.bibek.core.utils.alarm.cancelScheduledAlarm
import com.bibek.planner.R
import com.bibek.planner.presentation.ui.components.RecipeAlarmCard
import com.bibek.planner.presentation.ui.components.WeekdaysRow

@Composable
fun ScheduleRecipeScreen(
    uiState: ScheduleRecipeAlarmState,
    onEvent: (ScheduleRecipeAlarmEvent) -> Unit
) {
    ScheduleRecipeUI(uiState = uiState, onEvent = onEvent)
}

@Composable
fun ScheduleRecipeUI(
    uiState: ScheduleRecipeAlarmState,
    onEvent: (ScheduleRecipeAlarmEvent) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground),
        topBar = {
            TopBar(title = stringResource(R.string.remainders)) {
                onEvent(ScheduleRecipeAlarmEvent.NavigateBack)
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackground)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            WeekdaysRow(selectedDay = uiState.selectedDay, onClick = { day ->
                onEvent(ScheduleRecipeAlarmEvent.OnDaySelect(day))
            })
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.recipeAlarmList) { item ->
                    RecipeAlarmCard(
                        image = item.image ?: return@items,
                        recipeName = item.name,
                        time = item.time,
                        onClick = {
                            onEvent(ScheduleRecipeAlarmEvent.NavigateToRecipeAlarmDetails(recipeId = item.recipeId))
                        }, onDeletedClick = {
                            cancelScheduledAlarm(
                                alarmId = item.alarmId ?: return@RecipeAlarmCard,
                                context = context
                            )
                            onEvent.invoke(ScheduleRecipeAlarmEvent.DeleteAlarm(item.alarmId))
                        }
                    )
                }
            }
        }
    }
}
