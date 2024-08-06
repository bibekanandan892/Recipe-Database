package com.bibek.recipedatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bibek.core.data.local.dao.RecipeAlarmDao
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.core.utils.type_converter.type.BitmapTypeConverter
import com.bibek.core.utils.type_converter.type.IngredientListConverter
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.local.model.search.RecipeEntity
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Database(entities = [RecipeEntity::class,RecipeAlarmEntity::class], version = 2, exportSchema = false)
@TypeConverters(IngredientListConverter::class,BitmapTypeConverter::class)
abstract class  RecipeDatabase : RoomDatabase(){
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeAlarmDao() : RecipeAlarmDao
}