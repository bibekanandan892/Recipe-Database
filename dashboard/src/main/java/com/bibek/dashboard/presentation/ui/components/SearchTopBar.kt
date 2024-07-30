package com.bibek.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bibek.dashboard.presentation.ui.home.HomeEvent

@Composable
fun SearchTopBar(
    query: String,
    onEvent: (HomeEvent) -> Unit,
    navigationIcon: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            navigationIcon.invoke()
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp)
                .padding(bottom = 10.dp),
                value = query,
                onValueChange = {
                    onEvent.invoke(HomeEvent.OnQueryChange(it))
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                placeholder = {
                    Text(text = "Search...")
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Black,
                    cursorColor = Color.Black
                )
            )
        }
    }
}