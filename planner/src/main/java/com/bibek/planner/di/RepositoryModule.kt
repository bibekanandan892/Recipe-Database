package com.bibek.planner.di

import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.planner.data.repository.ScheduleRecipeRepositoryImpl
import com.bibek.planner.domain.repository.ScheduleRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePlannerRepository(recipeAlarmDao: RecipeAlarmDao): ScheduleRecipeRepository =
        ScheduleRecipeRepositoryImpl(recipeAlarmDao = recipeAlarmDao)
}