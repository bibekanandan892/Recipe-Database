package com.bibek.dashboard.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.bibek.core.ui.color.theme.ColorBackground
import com.bibek.core.ui.color.theme.ColorTextFieldContainerDefault
import com.bibek.core.ui.color.theme.ColorTextFieldText
import com.bibek.core.ui.color.theme.ColorTextTitle
import com.bibek.dashboard.presentation.ui.home.HomeEvent

@Composable
fun SearchTopBar(
    query: String,
    onEvent: (HomeEvent) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 10.dp)
            .background(ColorBackground), horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .background(ColorBackground)
                .widthIn(
                    max = 500.dp
                )
                .weight(0.8f)
        ) {
            SearchBar(query, onEvent, keyboardController)
        }
        IconButton(
            onClick = { onEvent(HomeEvent.NavigateToScheduleRecipe) },
            modifier = Modifier.weight(0.1f)
        ) {
            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Remainder")
        }
    }
}


@Composable
private fun SearchBar(
    query: String,
    onEvent: (HomeEvent) -> Unit,
    keyboardController: SoftwareKeyboardController?
) {
    Row(
        modifier = Modifier.widthIn(max = 500.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = query,
            onValueChange = {
                onEvent.invoke(HomeEvent.OnQueryChange(it))
            },
            leadingIcon = {
                Icon(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search", tint = ColorTextTitle
                )
            },
            placeholder = {
                Text(text = "Search...")
            },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = ColorTextFieldText,
                focusedIndicatorColor = ColorTextFieldContainerDefault,
                unfocusedIndicatorColor = ColorTextFieldContainerDefault,
                disabledIndicatorColor = ColorTextFieldContainerDefault,
                backgroundColor = ColorTextFieldContainerDefault
            ), trailingIcon = {
                if (query.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .background(color = ColorTextTitle, RoundedCornerShape(50))
                            .padding(2.dp)
                            .clickable {
                                onEvent(HomeEvent.OnQueryChange(""))
                                keyboardController?.hide()
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        )
    }
}
