package com.bibek.dashboard.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bibek.dashboard.data.model.scarch.res.Recipe


@Dao
interface  RecipeDao {

    @Upsert
    suspend fun upsertAll(recipeList: List<Recipe>)

    @Query("SELECT * FROM Recipe_Table")
    fun getRecipePagingSource() : PagingSource<Int,Recipe>


    @Query("DELETE FROM Recipe_Table")
    suspend fun deleteAll()
}