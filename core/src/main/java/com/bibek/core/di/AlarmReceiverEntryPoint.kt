package com.bibek.core.di

import com.bibek.core.data.local.dao.RecipeAlarmDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AlarmReceiverEntryPoint {
    fun recipeAlarmDao(): RecipeAlarmDao
}