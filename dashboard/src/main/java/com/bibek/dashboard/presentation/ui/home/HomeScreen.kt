package com.bibek.dashboard.presentation.ui.home

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import androidx.paging.LoadState
import com.bibek.dashboard.domain.model.search.response.Recipe

@Composable
fun HomeScreen(
    recipeList: LazyPagingItems<Recipe>,
    onEvent: (HomeEvent) -> Unit = {}
) {
    HomeUI(recipeList = recipeList, onEvent)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeUI(recipeList: LazyPagingItems<Recipe>, onEvent: (HomeEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val context = LocalContext.current
        val loadState = recipeList.loadState
        LaunchedEffect(key1 = loadState) {
            if (loadState.refresh is LoadState.Error){
                Toast.makeText(context,"Error" , Toast.LENGTH_SHORT).show()
            }
        }
        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            query = "query",
            onQueryChange = {
                onEvent.invoke(HomeEvent.OnQueryChange(it))
            },
            onSearch = {
                onEvent.invoke(HomeEvent.OnSearchClick)
            },
            active = false,
            onActiveChange = {}) {
        }
        if (recipeList.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(recipeList.itemCount) { index ->
                    val recipe = recipeList[index]
                    if (recipe != null) {
                        RecipeRow(recipe = recipe)
                    }
                }
                item {
                    if (recipeList.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}