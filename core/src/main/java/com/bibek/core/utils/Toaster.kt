package com.bibek.core.utils

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 *Author: Bibekananda Nayak
 *
 *Date: 2024-02-21
 *
 *Description:
 *This Kotlin class represents a Toaster, implemented as a singleton using the @Singleton annotation.
 *It provides two shared flows, successFlow and errorFlow, to emit success and error messages respectively.
 *These flows can be collected by observing components to handle success and error events asynchronously.
 *The class also provides suspend functions success() and error() to emit messages to their respective flows.
 */

@Stable
@Singleton
class Toaster @Inject constructor() {
    private val _successFlow = MutableSharedFlow<String>() // Flow for emitting success messages
    val successFlow: Flow<String> = _successFlow // Exposed immutable flow for success messages

    private val _errorFlow = MutableSharedFlow<String>() // Flow for emitting error messages
    val errorFlow: Flow<String> = _errorFlow // Exposed immutable flow for error messages

    // Function to emit a success message
    suspend fun success(message: String) {
        _successFlow.emit(message)
    }

    // Function to emit an error message
    suspend fun error(message: String) {
        _errorFlow.emit(message)
    }
}
