package com.bibek.planner.presentation.ui.recipe_alarm_details_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bibek.core.ui.components.IngredientsItem
import com.bibek.core.ui.components.TopBar
import com.bibek.planner.R
import com.bibek.planner.presentation.ui.components.RecipeCard

@Composable
fun RecipeAlarmDetailsScreen(
    uiState: RecipeAlarmDetailsState,
    onEvent: (RecipeAlarmDetailsEvent) -> Unit = {}
) {
    RecipeDetailsContent(uiState, onEvent)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipeDetailsContent(uiState: RecipeAlarmDetailsState, onEvent: (RecipeAlarmDetailsEvent) -> Unit = {}) {
    Scaffold(topBar = {
        TopBar(title = stringResource(id = R.string.recipe_details)) {
            onEvent(RecipeAlarmDetailsEvent.NavigateBack)
        }
    } ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    RecipeCard(image = uiState.imageBitmap ?: return@item, title = uiState.name)
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        ),
                        text = stringResource(R.string.ingredients),
                        fontSize = TextUnit(6f, TextUnitType.Em),
                        style = TextStyle(fontFamily = FontFamily.SansSerif),
                        fontWeight = FontWeight.Bold
                    )
                }
                items(uiState.ingredients.size) {
                    val item = uiState.ingredients[it]
                    IngredientsItem(
                        nameClean = item.nameClean ?: return@items,
                        originalName = item.originalName ?: return@items
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

}
