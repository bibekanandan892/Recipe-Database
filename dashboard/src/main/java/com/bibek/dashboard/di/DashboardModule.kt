package com.bibek.dashboard.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.model.scarch.res.Recipe
import com.bibek.dashboard.data.remote.RecipeRemoteMediator
import com.bibek.dashboard.data.repository.RecipeRepositoryImpl
import com.bibek.dashboard.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


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