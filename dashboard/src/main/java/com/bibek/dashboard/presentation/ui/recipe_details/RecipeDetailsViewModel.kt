package com.bibek.dashboard.presentation.ui.recipe_details

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.core.utils.DEFAULT_IMAGE
import com.bibek.core.utils.DEFAULT_NAME
import com.bibek.core.utils.DETAILS_PREFIX
import com.bibek.core.utils.NAME_PREFIX
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.formatTo12Hour
import com.bibek.core.utils.navigation.Navigator
import com.bibek.core.utils.network.collectResponse
import com.bibek.dashboard.data.mapper.toIngredientsEntity
import com.bibek.dashboard.domain.usecase.GetRecipeDetailsUseCase
import com.bibek.dashboard.domain.usecase.SaveRecipeAlarmUseCase
import com.bibek.dashboard.utils.getCurrentTimeIn12HourFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val saveRecipeAlarmUseCase: SaveRecipeAlarmUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsState(selectedTime = getCurrentTimeIn12HourFormat()))
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: RecipeDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                RecipeDetailsEvent.OnAddRecipeClick -> {

                }
                RecipeDetailsEvent.OnViewMore -> {}
                is RecipeDetailsEvent.GetRecipe -> getRecipeDetails(event.recipeId)
                RecipeDetailsEvent.NavigateBack -> navigator.back()
                is RecipeDetailsEvent.SetRecipeAlarm -> setRecipeAlarm(event.image, alarmId = event.alarmId)
                RecipeDetailsEvent.OnTimeClick -> _uiState.update { uiState ->
                    uiState.copy(
                        isShowClock = true
                    )
                }
                RecipeDetailsEvent.OnCloseClock -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isShowClock = false
                        )
                    }
                }

                is RecipeDetailsEvent.OnTimeSelect -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            selectedTime = formatTo12Hour(
                                event.hour,
                                event.minutes
                            ),
                            hour = event.hour,
                            minute = event.minutes
                        )
                    }
                }

                is RecipeDetailsEvent.OnDaySelect -> _uiState.update { uiState ->
                    uiState.copy(
                        selectedDay = event.day
                    )
                }
            }
        }
    }

    private suspend fun setRecipeAlarm(image: Bitmap, alarmId: Int) {
        saveRecipeAlarmUseCase.invoke(
            recipeAlarmEntity = RecipeAlarmEntity(
                id = alarmId,
                hour = uiState.value.hour,
                minute = uiState.value.minute,
                dayOfWeek = uiState.value.selectedDay?.dayOfWeek,
                ingredients = uiState.value.ingredients.map { it.toIngredientsEntity() },
                isRepeat = true,
                recipeId = uiState.value.id.toString(),
                name = uiState.value.name,
                image = image
            )
        )
    }

    private suspend fun getRecipeDetails(recipeId: String) {
        getRecipeDetailsUseCase.invoke(recipeId = recipeId).collectResponse(onSuccess = {
            _uiState.update { uiState ->
                uiState.copy(
                    id = it?.id,
                    name = it?.title ?: DEFAULT_NAME,
                    image = it?.image ?: DEFAULT_IMAGE,
                    ingredients = it?.extendedIngredients?.filterNotNull()?.map {
                        it.copy(
                            nameClean = "$NAME_PREFIX${it.nameClean}",
                            originalName = "$DETAILS_PREFIX${it.originalName}"
                        )
                    } ?: listOf()
                )
            }
        }, onLoading = {
            _uiState.update { uiState -> uiState.copy(isRecipeDetailsLoading = it) }
        }, onError = {
            _uiState.update { uiState ->
                uiState.copy(
                    name = "", image = "", ingredients = listOf()
                )
            }
            toaster.error(it)
        })
    }
}
