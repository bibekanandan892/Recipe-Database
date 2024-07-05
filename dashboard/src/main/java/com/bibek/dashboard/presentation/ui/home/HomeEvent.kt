package com.bibek.dashboard.presentation.ui.home

sealed interface HomeEvent {
    data class OnQueryChange(val query: String) : HomeEvent
    data object OnSearchClick : HomeEvent
}