package com.bibek.dashboard.presentation.ui.home

sealed class HomeEvent {
    data class OnQueryChange(val query: String) : HomeEvent()
    data object OnSearchClick : HomeEvent()
    data class NavigateToRecipeDetails(val recipeId : String) : HomeEvent()
}