package com.bibek.dashboard.di

import android.content.Context
import androidx.room.Room
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): RecipeDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "RECIPE_DATABASE"
        )
            .fallbackToDestructiveMigration()
            .build()
    @Singleton
    @Provides
    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao = recipeDatabase.recipeDao()


}