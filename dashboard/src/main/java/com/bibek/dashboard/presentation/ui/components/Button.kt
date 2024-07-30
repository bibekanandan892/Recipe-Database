package com.bibek.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color

@Composable
fun Button(title: String,onClick : () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onClick,
            Modifier
                .height(65.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ), elevation = ButtonDefaults.elevation(7.dp)
        ) {
            Text(text = title)
        }
    }
}