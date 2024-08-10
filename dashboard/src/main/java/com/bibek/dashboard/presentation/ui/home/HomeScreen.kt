package com.bibek.dashboard.presentation.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bibek.core.ui.color.theme.ColorBackground
import com.bibek.dashboard.R
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import com.bibek.dashboard.presentation.ui.components.SearchTopBar

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
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground),
        topBar = {
            Text(
                text = stringResource(R.string.find_best_recipe_for_cooking),
                fontSize = TextUnit(7f, TextUnitType.Em),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .background(ColorBackground)
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    )

            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackground)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            SearchTopBar(query, onEvent)
            if (recipeList.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Black
                )
            } else if (recipeList.itemCount == 0) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.no_recipe_available))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(recipeList.itemCount) { index ->
                        val recipe = recipeList[index]
                        if (recipe != null) {
                            RecipeRow(recipe = recipe, height = 300.dp, onItemClick = {
                                onEvent(HomeEvent.NavigateToRecipeDetails(recipe.id.toString()))
                            })
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
                        if (recipeList.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeUiPreview() {
    HomeScreen(uiState = HomeState())
}

