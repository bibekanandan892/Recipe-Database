package com.bibek.dashboard.data.local

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.bibek.dashboard.data.model.scarch.res.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {

    @Transaction
    suspend fun refreshRecipes(loadType: LoadType, recipeList: List<Recipe>) {
        if (loadType == LoadType.REFRESH) {
            deleteAll()
        }
        upsertAll(recipeList)
    }


    @Upsert
    suspend fun upsertAll(recipeList: List<Recipe>)

    @Insert
    suspend fun insertRecipeList(recipeList: List<Recipe>)

    @Query("SELECT * FROM Recipe_Table")
    fun getAllRecipe() : Flow<List<Recipe>>



    @Query("SELECT * FROM Recipe_Table")
    fun getRecipePagingSource() : PagingSource<Int,Recipe>


    @Query("DELETE FROM Recipe_Table")
    suspend fun deleteAll()
}