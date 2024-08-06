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
import com.bibek.core.ui.color.theme.ColorBackground
import com.bibek.core.ui.components.TopBar
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
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground),
        topBar = {
            TopBar(title = "Remainders") {
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
            LazyColumn {
                items(uiState.recipeAlarmList) { item ->
                    RecipeAlarmCard(
                        image = item.image ?: return@items,
                        recipeName = item.name,
                        time = item.time,
                        onClick = {
                            onEvent(ScheduleRecipeAlarmEvent.NavigateToRecipeAlarmDetails(recipeId = item.recipeId))
                        })
                }
            }
        }
    }
}
