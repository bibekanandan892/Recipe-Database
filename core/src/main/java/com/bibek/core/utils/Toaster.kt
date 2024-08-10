package com.bibek.core.utils

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton
@Stable
@Singleton
class Toaster @Inject constructor() {
    private val _successFlow = MutableSharedFlow<String>()
    val successFlow: Flow<String> = _successFlow
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: Flow<String> = _errorFlow

    suspend fun success(message: String) {
        _successFlow.emit(message)
    }

    suspend fun error(message: String) {
        _errorFlow.emit(message)
    }
}
