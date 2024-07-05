package com.bibek.dashboard.di

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
        recipeDao: RecipeDao
    ): RecipeRepository =
        RecipeRepositoryImpl(httpClient = httpClient,recipeDao  = recipeDao)
}