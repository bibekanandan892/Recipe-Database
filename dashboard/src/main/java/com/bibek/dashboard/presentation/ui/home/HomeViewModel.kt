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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase,
    private val toaster: Toaster,
    private val navigator: Navigator,
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState get() = _uiState.asStateFlow()
    val recipePager: Flow<PagingData<Recipe>> =
        searchRecipeUseCase.invoke("", "", "", "")
            .cachedIn(viewModelScope)
    private fun getRecipe(query: String = "") {
        //todo
    }
    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when(event){
                is HomeEvent.OnQueryChange -> {
                    //todo
                }
                HomeEvent.OnSearchClick -> {
                    //todo
                }
            }
        }
    }
}