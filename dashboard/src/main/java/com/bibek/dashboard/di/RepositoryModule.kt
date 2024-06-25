package com.bibek.dashboard.di

import com.bibek.dashboard.data.repository.RecipeRepositoryImpl
import com.bibek.dashboard.domain.repository.RecipeRepository
import com.bibek.dashboard.domain.usecase.SearchRecipeUseCase
import com.bibek.dashboard.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun provideRecipeRepository(httpClient: HttpClient): RecipeRepository =
        RecipeRepositoryImpl(httpClient)

    @Provides
    fun provideUseCases(recipeRepository: RecipeRepository) =
        UseCases(searchRecipeUseCase = SearchRecipeUseCase(recipeRepository))
}