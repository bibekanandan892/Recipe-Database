package com.bibek.dashboard.presentation.ui.recipe_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.navigation.Navigator
import com.bibek.core.utils.network.collectResponse
import com.bibek.dashboard.domain.usecase.GetRecipeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsState())
    val uiState get() = _uiState.asStateFlow()

    fun onEvent(event: RecipeDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                RecipeDetailsEvent.OnAddRecipeClick -> {}
                RecipeDetailsEvent.OnViewMore -> {}
                is RecipeDetailsEvent.GetRecipe -> {
                    getRecipeDetails(event.recipeId)
                }

                RecipeDetailsEvent.NavigateBack -> navigator.back()
            }
        }
    }

    private suspend fun getRecipeDetails(recipeId: String) {
        getRecipeDetailsUseCase.invoke(recipeId = recipeId).collectResponse(
            onSuccess = {
                _uiState.update { uiState ->
                    uiState.copy(
                        title = it?.title ?: "NA",
                        image = it?.image ?: "NA",
                        ingredients = it?.extendedIngredients?.filterNotNull()
                            ?.map {
                                it.copy(
                                    nameClean = "Name : ${it.nameClean}",
                                    originalName = "Details : ${it.originalName}"
                                )
                            }
                            ?: listOf()
                    )
                }
            }, onLoading = {
                _uiState.update { uiState -> uiState.copy(isRecipeDetailsLoading = it) }
            }, onError = {
                _uiState.update { uiState ->
                    uiState.copy(
                        title = "",
                        image = "",
                        ingredients = listOf()
                    )
                }
                toaster.error(it)
            }
        )
    }
}