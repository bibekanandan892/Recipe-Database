package com.bibek.dashboard.presentation.ui.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.bibek.core.ui.color.theme.ColorPrimary
import com.bibek.dashboard.R
import com.bibek.dashboard.domain.model.search.response.Recipe

@ExperimentalAnimationApi
@Composable
fun RecipeRow(
    recipe: Recipe,
    onItemClick: (Int) -> Unit = {},
    isLoadBitmap : Boolean = false,
    onImageLoaded: (Bitmap) -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(recipe.image)
    val painterState = painter.state
    if (isLoadBitmap && painterState is AsyncImagePainter.State.Success) {
        // Extract the bitmap from the painter's result
        val imageBitmap = (painterState.result.drawable as BitmapDrawable).bitmap
        onImageLoaded(imageBitmap)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(recipe.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(26.dp)),
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(450.dp),
                contentAlignment = Alignment.BottomStart,
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.recipe_image),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                if (painter.state is AsyncImagePainter.State.Loading) {
                    Box(
                        modifier = Modifier
                            .height(450.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = ColorPrimary)
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
                        ), contentAlignment = Alignment.BottomStart
                ){
                    Column {
                        Text(
                            text = recipe.title,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = TextUnit(6f,TextUnitType.Em),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }


                }
            }
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun RecipeRow(
    painter: AsyncImagePainter,
    title: String

) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(26.dp)),
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(450.dp),
                contentAlignment = Alignment.BottomStart,
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.recipe_image),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
                if (painter.state is AsyncImagePainter.State.Loading) {
                    Box(
                        modifier = Modifier
                            .height(450.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = ColorPrimary)
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
                        ), contentAlignment = Alignment.BottomStart
                ){
                    Column {
                        Text(
                            text = title,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            fontSize = TextUnit(6f,TextUnitType.Em),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }


                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Preview(showSystemUi = true)
@Composable
fun RecipeRowUI() {
    RecipeRow(recipe = Recipe(title = "long time coming"))
}