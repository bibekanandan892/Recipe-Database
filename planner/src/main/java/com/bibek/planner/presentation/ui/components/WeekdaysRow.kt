package com.bibek.planner.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.bibek.core.utils.Day

@Composable
fun WeekdaysRow(selectedDay: Day,onClick: (Day) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(com.bibek.core.utils.Day.entries) { day ->
            DayItem(day ,selectedDay, onClick = {onClick.invoke(day)})
        }
    }
}

@Composable
fun DayItem(day: Day, selectedDay : Day, onClick : ()-> Unit = {}) {
    Card(
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .height(50.dp)
            .clickable { onClick.invoke() },
        elevation = 7.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
                .background(color = if(selectedDay == day) Color.Black else Color.White)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = day.dayName,
                color = if(selectedDay == day) Color.White else Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(value = 3f, TextUnitType.Em)
            )
        }

    }
}