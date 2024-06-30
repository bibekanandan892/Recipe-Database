package com.bibek.dashboard.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.bibek.core.utils.Navigator
import com.bibek.core.utils.Toaster
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.dashboard.domain.usecase.SearchRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.PagingData
import com.bibek.core.utils.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest


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
//        viewModelScope.launch { getSearchRecipe() }
//        getRecipe()
        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            navigator.navigate(Destination.RecipeDetails.name)
            delay(2000)
            toaster.error("error")
            delay(1000)
            navigator.back()

        }
    }

    private fun getRecipe(query: String = "") {


    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
//            when(event){
//                is HomeEvent.OnQueryChange -> {
//                    _uiState.update { it.copy(query = event.query) }
//                    searchQuery.emit(event.query)
//
//                }
//                HomeEvent.OnSearchClick -> {
//                    getRecipe()
//                }
//            }
        }
    }

    suspend fun getSearchRecipe() {
//        searchQuery.debounce(300).distinctUntilChanged().onEach {
//            getRecipe(query = it)
//        }
    }


}