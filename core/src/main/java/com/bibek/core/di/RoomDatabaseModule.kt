package com.bibek.core.di

import android.content.Context
import androidx.room.Room
import com.bibek.core.room.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {

    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context)= Room.databaseBuilder(
    context.applicationContext,
        RecipeDatabase::class.java,
    "RECIPE_DATABASE"
    )
    .fallbackToDestructiveMigration()
    .build()
}