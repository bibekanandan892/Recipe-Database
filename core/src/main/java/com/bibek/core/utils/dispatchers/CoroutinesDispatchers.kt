package com.bibek.core.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface CoroutinesDispatchers {
    fun main() : MainCoroutineDispatcher
    fun io() : CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun mainImmediate() : MainCoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}