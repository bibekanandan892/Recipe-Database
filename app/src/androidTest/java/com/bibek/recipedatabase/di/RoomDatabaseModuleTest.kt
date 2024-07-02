package com.bibek.recipedatabase.di

import android.content.Context
import androidx.room.Room
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.recipedatabase.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RoomDatabaseModule::class]
)
object RoomDatabaseModuleTest {


    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): RecipeDatabase =
        Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
        )
            .allowMainThreadQueries()
            .build()

    @Singleton
    @Provides
    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao = recipeDatabase.recipeDao()


}