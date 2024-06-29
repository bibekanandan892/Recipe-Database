package com.bibek.dashboard.presentation.ui.home

import androidx.paging.PagingData
import androidx.room.Query
import com.bibek.dashboard.data.model.scarch.res.Recipe
import kotlinx.coroutines.flow.Flow

data class  HomeState(
    val isLoading: Boolean = false,
    val recipeList : List<Recipe> = listOf(),
    val query: String = "",
    val recipePager : Flow<PagingData<Recipe>>
)

sealed interface HomeEvent{
    data class OnQueryChange(val query: String) : HomeEvent
    data object OnSearchClick : HomeEvent

}
