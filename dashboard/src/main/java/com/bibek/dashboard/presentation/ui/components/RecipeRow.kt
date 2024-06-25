package com.bibek.dashboard.presentation.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.bibek.core.utils.checkNull
import com.bibek.dashboard.data.model.scarch.res.Recipe

@ExperimentalAnimationApi
@Composable
@Preview
fun RecipeRow(
    recipe: Recipe = Recipe(title = "", image = "https://img.spoonacular.com/recipes/715415-312x231.jpg"),
    onItemClick: (Int) -> Unit = {}
) {

    val painter = rememberAsyncImagePainter(recipe.image)
    if (painter.state is AsyncImagePainter.State.Loading) {
//        CircularProgressIndicator(color = Primary)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp)
            .clickable {
                recipe.id?.let { onItemClick(it) }

            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {


        Column(modifier = Modifier.padding(4.dp)) {
            Image(
                painter = painter,
                contentDescription = "Movie Poster",
                Modifier
                    .heightIn(100.dp,400.dp)
                    .width(280.dp)
            )
            Text(
                text = "Recipe Name : ${recipe.title.checkNull().take(20)}...",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

        }


    }


}