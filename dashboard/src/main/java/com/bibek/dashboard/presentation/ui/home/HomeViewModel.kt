package com.bibek.dashboard.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.connectivity.ConnectionState
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.core.utils.navigation.Destination
import com.bibek.core.utils.navigation.Navigator
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.domain.usecase.SearchRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@Suppress("unused")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
    connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState get() = _uiState.asStateFlow()
    private val _queryFlow = MutableSharedFlow<String>(replay = 1)
    init {
        getRecipe()
        viewModelScope.launch {
            _queryFlow
                .debounce(600)
                .distinctUntilChanged()
                .collect { query ->
                    getRecipe(query = query)
                }
        }
        connectivityObserver.connectionState.onEach {
            if(it == ConnectionState.Available){
                getRecipe(query = uiState.value.query)
            }
        }.launchIn(viewModelScope)
    }
    private fun getRecipe(query: String = "") {
        _uiState.update { uiState ->
            val recipePager: Flow<PagingData<Recipe>> =
                searchRecipeUseCase.invoke(query, "", "", "")
                    .cachedIn(viewModelScope)
            uiState.copy(recipePager = recipePager)
        }
    }
    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.OnQueryChange -> {
                    _uiState.update { uiState -> uiState.copy(query = event.query) }
                    _queryFlow.emit(event.query)
                }
                HomeEvent.OnSearchClick -> {
                    //todo
                }

                is HomeEvent.NavigateToRecipeDetails -> {
                    navigator.navigate(destination = Destination.RECIPE_DETAILS.name + "/${event.recipeId}")
                }

                HomeEvent.NavigateToScheduleRecipe -> navigator.navigate(Destination.SCHEDULE_RECIPE.name)
            }
        }
    }
}