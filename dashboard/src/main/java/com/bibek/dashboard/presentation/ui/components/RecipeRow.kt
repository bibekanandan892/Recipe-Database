package com.bibek.dashboard.presentation.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.bibek.dashboard.R
import com.bibek.dashboard.domain.model.search.response.Recipe

@ExperimentalAnimationApi
@Composable
fun RecipeRow(
    recipe: Recipe,
    onItemClick: (Int) -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(recipe.image)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(recipe.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .padding(4.dp),
                contentAlignment = Alignment.BottomStart,
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.recipe_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                if (painter.state is AsyncImagePainter.State.Loading) {
                    Box(
                        modifier = Modifier
                            .height(190.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.7f), Color.Transparent
                                ), startY = Float.POSITIVE_INFINITY, endY = 0.5f
                            ), shape = RoundedCornerShape(15.dp)
                        )
                )
            }
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showSystemUi = true)
@Composable
fun RecipeRowUI(modifier: Modifier = Modifier) {
    RecipeRow(recipe = Recipe(title = "long time coming"))
}