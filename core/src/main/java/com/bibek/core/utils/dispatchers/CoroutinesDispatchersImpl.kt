package com.bibek.core.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

class CoroutinesDispatchersImpl : CoroutinesDispatchers {
    override fun main(): MainCoroutineDispatcher = Dispatchers.Main
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun default(): CoroutineDispatcher = Dispatchers.Default
    override fun mainImmediate(): MainCoroutineDispatcher = Dispatchers.Main.immediate
    override fun unconfined(): CoroutineDispatcher= Dispatchers.Unconfined
}