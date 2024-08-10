package com.bibek.core.di

import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.utils.dispatchers.CoroutinesDispatchers
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AlarmReceiverEntryPoint {
    fun recipeAlarmDao(): RecipeAlarmDao
    fun coroutinesDispatchers() : CoroutinesDispatchers
    fun coroutineExceptionHandler() : CoroutineExceptionHandler
}