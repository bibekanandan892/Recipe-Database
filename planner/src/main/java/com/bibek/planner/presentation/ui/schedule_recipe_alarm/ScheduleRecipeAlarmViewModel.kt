package com.bibek.planner.presentation.ui.schedule_recipe_alarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.navigation.Destination
import com.bibek.core.utils.navigation.Navigator
import com.bibek.planner.domain.usecase.GetRecipeAlarmListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleRecipeAlarmViewModel @Inject constructor(
    private val getRecipeAlarmListUseCase: GetRecipeAlarmListUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleRecipeAlarmState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getRecipeAlarmListUseCase.invoke()
            .combine(uiState) { recipeAlarmEntities, scheduleRecipeAlarmState ->
                _uiState.update { uiState ->
                    uiState.copy(
                        recipeAlarmList = recipeAlarmEntities.filter {
                            it.dayOfWeek == scheduleRecipeAlarmState.selectedDay.dayOfWeek
                        }
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun onEvent(event: ScheduleRecipeAlarmEvent) {
        viewModelScope.launch {
            when (event) {
                is ScheduleRecipeAlarmEvent.NavigateToRecipeAlarmDetails -> {
                    navigator.navigate(destination = Destination.RECIPE_ALARM_DETAILS.name + "/${event.recipeId}")
                }
                ScheduleRecipeAlarmEvent.NavigateBack -> navigator.back()
                is ScheduleRecipeAlarmEvent.OnDaySelect -> _uiState.update { uiState ->
                    uiState.copy(
                        selectedDay = event.day
                    )
                }
            }
        }
    }
}