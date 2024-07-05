package com.bibek.dashboard.presentation.ui.home

import com.bibek.dashboard.data.remote.model.search.response.RecipeDto

data class HomeState(
    val isLoading: Boolean = false,
    val recipeDtoList: List<RecipeDto> = listOf(),
    val query: String = "",
)


