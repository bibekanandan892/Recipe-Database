package com.bibek.recipedatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.local.model.search.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
}