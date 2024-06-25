package com.bibek.dashboard.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import com.bibek.dashboard.presentation.ui.splash.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(recipeResponse: MutableState<List<Recipe>>, isShowSplash: Boolean,) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if(isShowSplash){
            SplashScreen()
        }else{
            HomeUI(recipeResponse = recipeResponse)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeUI(recipeResponse: MutableState<List<Recipe>>) {
    SearchBar(modifier = Modifier.fillMaxWidth(),
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {}) {
    }
    LazyVerticalGrid(columns = GridCells.Adaptive(200.dp), modifier = Modifier.fillMaxWidth(),) {
        items(recipeResponse.value.size){
            RecipeRow(recipeResponse.value[it])
        }
    }

}