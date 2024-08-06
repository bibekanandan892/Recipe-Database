package com.bibek.dashboard.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bibek.core.utils.Day

@Composable
fun ReminderContent(
    closeSelection: () -> Unit,
    onTimeSelect: (Int, Int) -> Unit,
    onSaveClick: () -> Unit = {},
    onDayClick: (Day) -> Unit = {},
    onTimeClick : ()-> Unit = {},
    onCloseSheet : ()-> Unit = {},
    isShowClock: Boolean,
    selectedDay: Day? = null,
    selectedTime: String
) {
    if (isShowClock) {
        ClockDialog(closeSelection =  {
            closeSelection()
        }, onTimeSelect = {hour, min->
            onTimeSelect(hour,min)
        })
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1C1C1E))
            .clip(shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Select Time", color = Color.White) },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = onCloseSheet) {
                    Icon(Icons.Default.Close, contentDescription = "Back", tint = Color.White)
                }
            },
            actions = {
                selectedDay?.let {
                    IconButton(onClick = { onSaveClick.invoke() }) {
                        Icon(Icons.Default.Check, contentDescription = "Save", tint = Color.White)
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        // Time Display
        Text(
            text = selectedTime,
            fontSize = 64.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally).clickable(onClick = onTimeClick)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Schedule Switch
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Select Day", color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Days of the Week
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Day.entries.forEach { day ->
                DayCircle(
                    day = day,
                    isSelected = selectedDay?.name == day.name,
                    onClick = { onDayClick(day) }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun DayCircle(day: Day, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color.White else Color.DarkGray
    val textColor = if (isSelected) Color.DarkGray else Color.White
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.name.first().toString(), color = textColor, fontWeight = FontWeight.SemiBold)
    }
}
