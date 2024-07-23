package com.bibek.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bibek.dashboard.presentation.ui.home.HomeEvent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SearchTopBar(
    query: String,
    onEvent: (HomeEvent) -> Unit,
    navigationIcon: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Row (verticalAlignment = Alignment.CenterVertically){
            navigationIcon.invoke()
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp)
                .padding(bottom = 10.dp),
                query = query,
                onQueryChange = {
                    onEvent.invoke(HomeEvent.OnQueryChange(it))
                },
                onSearch = {
                    onEvent.invoke(HomeEvent.OnSearchClick)
                },
                active = false,
                onActiveChange = {

                }, leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }, placeholder = {
                    Text(text = "Search...")
                }
            ) {
            }
        }

    }
}