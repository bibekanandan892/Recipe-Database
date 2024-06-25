package com.bibek.core.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bibek.core.room.model.Recipe


@Dao
interface  RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(data: Recipe)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(dataTable: Recipe)

    @Delete
    suspend fun deleteData(dataTable: Recipe)

    @Query("DELETE FROM Recipe_Table")
    suspend fun deleteAllData()
}