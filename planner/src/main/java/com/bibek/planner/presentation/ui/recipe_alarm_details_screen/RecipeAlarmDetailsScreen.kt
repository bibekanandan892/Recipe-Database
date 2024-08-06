package com.bibek.planner.presentation.ui.recipe_alarm_details_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bibek.core.ui.components.IngredientsItem
import com.bibek.planner.presentation.ui.components.RecipeCard

@Composable
fun RecipeAlarmDetailsScreen(
    uiState: RecipeAlarmDetailsState,
) {
    RecipeDetailsContent(uiState)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipeDetailsContent(uiState: RecipeAlarmDetailsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn {
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
                    text = "Ingredients",
                    fontSize = TextUnit(6f, TextUnitType.Em),
                    style = TextStyle(fontFamily = FontFamily.SansSerif),
                    fontWeight = FontWeight.Bold
                )
            }
            items(uiState.ingredients.size) {
                val item = uiState.ingredients[it]
                IngredientsItem(
                    nameClean = item.nameClean ?: "return@items",
                    originalName = item.originalName ?: return@items
                )
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
