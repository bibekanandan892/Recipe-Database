package com.bibek.dashboard.presentation.ui.home

import androidx.paging.PagingData
import com.bibek.dashboard.data.remote.model.search.response.RecipeDto
import com.bibek.dashboard.domain.model.search.response.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val isLoading: Boolean = false,
    val recipeDtoList: List<RecipeDto> = listOf(),
    val query: String = "",
    val recipePager: Flow<PagingData<Recipe>> = flowOf()
)


