package com.bibek.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bibek.dashboard.data.remote.model.recipe_details.ExtendedIngredient

@Composable
 fun IngredientsItem(
    ingredient: ExtendedIngredient
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(), elevation = 6.dp, shape = RoundedCornerShape(20.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ingredient.nameClean.toString(),
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(text = ingredient.originalName.toString())
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}