package com.bibek.core.utils.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val connectionState: Flow<ConnectionState>
    val currentConnectionState: ConnectionState
}