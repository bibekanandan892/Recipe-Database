package com.bibek.dashboard.presentation.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibek.core.utils.Navigator
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.collectResponse
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases,
    private val toaster: Toaster,
    private val navigator: Navigator
) : ViewModel() {

    var recipeResponse = mutableStateOf<List<Recipe>>(listOf())
        private set


    var isShowSplash = mutableStateOf(true)
        private set

    init {
        getRecipe()
    }

    private fun getRecipe() {
        useCases.searchRecipeUseCase.invoke("", "", "", "")
            .collectResponse(viewModelScope,
                onError = { message ->
                    toaster.error(message)
                },
                onLoading = { loading ->
                    isShowSplash.value = loading
                },
                onSuccess = { response ->
                    if (response != null) {
                        recipeResponse.value = response.recipes?.filterNotNull() ?: listOf()
                    } else {
                        toaster.error("Something went wrong")
                    }
                })
    }


}