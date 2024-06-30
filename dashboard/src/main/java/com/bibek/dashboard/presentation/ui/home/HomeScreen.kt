package com.bibek.dashboard.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import androidx.paging.LoadState

@Composable
fun HomeScreen(uiState: HomeState, onEvent: (HomeEvent) -> Unit = {}) {

        HomeUI(recipeList = uiState.recipeList, uiState.query, onEvent)



}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeUI(recipeList: List<Recipe>, query: String, onEvent: (HomeEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            query = query,
            onQueryChange = {
                onEvent.invoke(HomeEvent.OnQueryChange(it))
            },
            onSearch = {
                onEvent.invoke(HomeEvent.OnSearchClick)
            },
            active = false,
            onActiveChange = {}) {
        }

        Spacer(modifier = Modifier.height(10.dp))

    }

}