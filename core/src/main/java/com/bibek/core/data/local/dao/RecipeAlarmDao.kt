package com.bibek.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity

@Dao
interface RecipeAlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeAlarm(recipeEntities: RecipeAlarmEntity)

    @Query("SELECT * FROM Recipe_Alarm_Table")
    suspend fun getAllRecipeAlarm() : List<RecipeAlarmEntity>

    @Query("DELETE FROM Recipe_Alarm_Table")
    suspend fun deleteRecipeAlarmAll()

    @Query("SELECT * FROM Recipe_Alarm_Table WHERE id = :id")
    suspend fun getRecipeAlarmById(id: Int): RecipeAlarmEntity?

    @Query("DELETE FROM Recipe_Alarm_Table WHERE id = :id")
    suspend fun deleteRecipeAlarmById(id: Int)
}