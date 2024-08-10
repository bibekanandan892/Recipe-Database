package com.bibek.core.di

import com.bibek.core.utils.dispatchers.CoroutinesDispatchers
import com.bibek.core.utils.dispatchers.CoroutinesDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutineModule {
    @Provides
    @Singleton
    fun providesDispatchers(): CoroutinesDispatchers = CoroutinesDispatchersImpl()
    @Provides
    @Singleton
    fun providesExceptionsHandler(): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }
}