package com.bibek.planner.presentation.ui.components

import android.graphics.Bitmap
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun RecipeCard(
    image: Bitmap,
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
                    bitmap = image.asImageBitmap(),
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
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
                            fontSize = TextUnit(6f, TextUnitType.Em),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }


                }
            }
        }
    }
}
