package com.bibek.dashboard.di

import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.repository.RecipeRepositoryImpl
import com.bibek.dashboard.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object DashboardModule {
    @Provides
    fun provideRecipeRepository(
        httpClient: HttpClient,
        recipeDao: RecipeDao,
        recipeAlarmDao: RecipeAlarmDao,
        connectivityObserver: ConnectivityObserver
    ): RecipeRepository = RecipeRepositoryImpl(
        httpClient = httpClient,
        recipeDao = recipeDao,
        connectivityObserver = connectivityObserver,
        recipeAlarmDao = recipeAlarmDao
    )
}