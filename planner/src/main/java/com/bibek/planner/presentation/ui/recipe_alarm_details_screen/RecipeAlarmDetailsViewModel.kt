package com.bibek.planner.presentation.ui.recipe_alarm_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.core.utils.GENERIC_ERROR_MESSAGE
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.navigation.Navigator
import com.bibek.planner.domain.usecase.GetRecipeAlarmItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeAlarmDetailsViewModel @Inject constructor(
    private val getRecipeAlarmItemUseCase: GetRecipeAlarmItemUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeAlarmDetailsState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: RecipeAlarmDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is RecipeAlarmDetailsEvent.GetRecipeAlarm -> getRecipeDetails(event.recipeId)
                RecipeAlarmDetailsEvent.NavigateBack -> navigator.back()
            }
        }
    }
    private suspend fun getRecipeDetails(recipeId: String) {
        val recipeAlarmItem = getRecipeAlarmItemUseCase.invoke(recipeId = recipeId)
        recipeAlarmItem?.let {
            _uiState.update { uiState ->
                uiState.copy(
                    recipeId = it.recipeId.toInt(),
                    name = it.name,
                    imageBitmap = it.image,
                    ingredients = it.ingredients
                )
            }
        }
        if(recipeAlarmItem == null){
            _uiState.update { uiState ->
                uiState.copy(
                    name = "", ingredients = listOf()
                )
            }
            toaster.error(GENERIC_ERROR_MESSAGE)
        }
    }
}