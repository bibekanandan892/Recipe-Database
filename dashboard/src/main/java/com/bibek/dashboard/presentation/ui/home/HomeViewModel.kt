package com.bibek.dashboard.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bibek.core.utils.navigation.Navigator
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.dashboard.domain.usecase.SearchRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bibek.dashboard.domain.model.search.response.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
@Suppress("unused")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState get() = _uiState.asStateFlow()
    init {
       getRecipe()
    }
    private fun getRecipe(query: String = "") {
        _uiState.update { uiState->
            val recipePager: Flow<PagingData<Recipe>> =
                searchRecipeUseCase.invoke(query, "", "", "")
                    .cachedIn(viewModelScope)
            uiState.copy(recipePager = recipePager)
        }
    }
    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when(event){
                is HomeEvent.OnQueryChange -> {
                    _uiState.update { uiState-> uiState.copy(query = event.query) }
                    getRecipe(query = event.query)
                }
                HomeEvent.OnSearchClick -> {
                    //todo
                }
            }
        }
    }
}