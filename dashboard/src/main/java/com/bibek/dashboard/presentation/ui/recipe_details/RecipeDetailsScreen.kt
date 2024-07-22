package com.bibek.dashboard.presentation.ui.recipe_details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bibek.dashboard.domain.model.search.response.Recipe
import com.bibek.dashboard.presentation.ui.components.IngredientsItem
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import kotlin.reflect.KFunction1

@Composable
fun RecipeDetailsScreen(
    uiState: RecipeDetailsState,
    onEvent: KFunction1<RecipeDetailsEvent, Unit>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (uiState.isRecipeDetailsLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.ingredients.isNotEmpty()) {
            RecipeDetailsUI(uiState = uiState)
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "No Recipe Details Found")
                    Button(onClick = { onEvent(RecipeDetailsEvent.NavigateBack) }) {
                        Text(text = "Go Back")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipeDetailsUI( uiState: RecipeDetailsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 10.dp
            ),
            text = "Recipe Details",
            fontSize = TextUnit(8f, TextUnitType.Em),
            style = TextStyle(fontFamily = FontFamily.Cursive)
        )
        RecipeRow(
            recipe = Recipe(
                image = uiState.image,
                title = uiState.title
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Button(onClick = { }) {
                Text(text = "Add Recipe")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { }) {
                Text(text = "Share")
                Spacer(modifier = Modifier.width(5.dp))
                Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = "Share")
            }
        }
        Text(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 10.dp
            ),
            text = "Ingredients",
            fontSize = TextUnit(6f, TextUnitType.Em),
            style = TextStyle(fontFamily = FontFamily.SansSerif)
        )
        LazyColumn {
            items(uiState.ingredients.size) {
                IngredientsItem(ingredient = uiState.ingredients[it])
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
private fun RecipeScreenPreview() {
    RecipeDetailsUI(uiState = RecipeDetailsState())
}