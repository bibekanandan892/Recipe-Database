package com.bibek.dashboard.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import com.bibek.dashboard.presentation.ui.components.SearchTopBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit = {},
) {
    val recipeList = uiState.recipePager.collectAsLazyPagingItems()
    HomeUI(query = uiState.query, recipeList = recipeList, onEvent)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeUI(query: String, recipeList: LazyPagingItems<Recipe>, onEvent: (HomeEvent) -> Unit) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                content = {}
            )
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            SearchTopBar(query, onEvent,navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                        .padding(top = 24.dp , start = 10.dp, end = 10.dp)
                        .size(30.dp),
                    contentDescription = null
                )
            })
        },) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
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
                                RecipeRow(recipe = recipe, onItemClick = {
                                    onEvent(HomeEvent.NavigateToRecipeDetails(recipe.id.toString()))
                                })
                            }
                            Spacer(modifier = Modifier.height(10.dp))
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
    }
}

