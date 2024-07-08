package com.bibek.recipedatabase.presentation.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bibek.core.utils.connectivity.ConnectionState
import com.bibek.recipedatabase.R
import kotlinx.coroutines.delay

@Composable
fun ConnectivityStatus(connectionState: ConnectionState) {
    var visibility by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(connectionState = connectionState)
    }
    LaunchedEffect(connectionState) {
        visibility = when (connectionState) {
            ConnectionState.Available -> {
                delay(2000)
                false
            }

            ConnectionState.Unavailable -> {
                true
            }
        }
    }
}

@Composable
fun ConnectivityStatusBox(connectionState: ConnectionState) {
    val isConnected = if (connectionState is ConnectionState.Available) {
        Color.Green
    } else {
        Color.Red
    }
    val backgroundColor by animateColorAsState(isConnected)
    val message = when (connectionState) {
        ConnectionState.Available -> {
            "Back Online!"
        }

        ConnectionState.Unavailable -> {
            "No Internet Connection!"
        }
    }
    val iconResource = when (connectionState) {
        ConnectionState.Available -> {
            R.drawable.internet
        }

        ConnectionState.Unavailable -> {
            R.drawable.no_internet
        }
    }
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painterResource(id = iconResource), "Connectivity Icon", tint = Color.White)
            Spacer(modifier = Modifier.size(8.dp))
            Text(message, color = Color.White, fontSize = 15.sp)
        }
    }
}