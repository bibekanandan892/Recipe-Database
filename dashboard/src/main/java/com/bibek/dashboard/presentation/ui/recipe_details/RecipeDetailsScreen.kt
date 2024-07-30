package com.bibek.dashboard.presentation.ui.recipe_details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bibek.core.ui.components.TopBar
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.presentation.ui.components.Button
import com.bibek.dashboard.presentation.ui.components.IngredientsItem
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import com.bibek.dashboard.presentation.ui.components.ReminderContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailsScreen(
    uiState: RecipeDetailsState,
    onEvent: (RecipeDetailsEvent) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    ModalBottomSheetLayout(sheetShape = RoundedCornerShape(
        topEnd = 20.dp,
        topStart = 20.dp
    ),
        sheetState = bottomSheetState,
        sheetContent = {
            ReminderContent(selectedTime = uiState.selectedTime,
                isShowClock = uiState.isShowClock, onTimeSelect = { hour, min ->
                    onEvent.invoke(RecipeDetailsEvent.OnTimeSelect(hour, min))
                }, closeSelection = {
                    onEvent(RecipeDetailsEvent.OnCloseClock)
                }, onSaveClick = {

                }, onDayClick = {
                    onEvent(RecipeDetailsEvent.OnDaySelect(it))
                }, onTimeClick = { onEvent(RecipeDetailsEvent.OnTimeClick) },
                onCloseSheet = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }, selectedDay = uiState.selectedDay)
        }
    ) {
        RecipeDetailsUI(uiState, onEvent, onAddRecipeClick = {
            scope.launch {
                bottomSheetState.show()
            }
        })
    }
}

@Composable
private fun RecipeDetailsUI(
    uiState: RecipeDetailsState,
    onEvent: (RecipeDetailsEvent) -> Unit, onAddRecipeClick: () -> Unit
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp), topBar = {
        TopBar("Recipe Details",
            onBackClick = { onEvent(RecipeDetailsEvent.NavigateBack) })
    }, floatingActionButton = {
        if (uiState.title.isNotEmpty() && uiState.ingredients.isNotEmpty()) {
            Button(title = "Add Recipe", onClick = onAddRecipeClick)
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (uiState.isRecipeDetailsLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (uiState.ingredients.isNotEmpty()) {
                RecipeDetailsContent(uiState = uiState)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No Recipe Details Found")
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipeDetailsContent(uiState: RecipeDetailsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn {
            item {
                RecipeRow(
                    Recipe(image = uiState.image, title = uiState.title)
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    ),
                    text = "Ingredients",
                    fontSize = TextUnit(6f, TextUnitType.Em),
                    style = TextStyle(fontFamily = FontFamily.SansSerif),
                    fontWeight = FontWeight.Bold
                )
            }
            items(uiState.ingredients.size) {
                IngredientsItem(ingredient = uiState.ingredients[it])
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun RecipeScreenPreview() {
    RecipeDetailsScreen(uiState = RecipeDetailsState())
}