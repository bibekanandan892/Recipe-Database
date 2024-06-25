package com.bibek.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bibek.core.room.dao.RecipeDao
import com.bibek.core.room.model.Recipe


@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao

}