package com.bibek.dashboard.data.local

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.bibek.dashboard.data.local.model.search.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {
    @Transaction
    suspend fun refreshRecipes(loadType: LoadType, recipeDtoList: List<RecipeEntity>) {
        if (loadType == LoadType.REFRESH) {
            deleteAll()
        }
        upsertAll(recipeDtoList)
    }

    @Upsert
    suspend fun upsertAll(recipeEntities: List<RecipeEntity>)

    @Insert
    suspend fun insertRecipeList(recipeEntities: List<RecipeEntity>)

    @Query("SELECT * FROM Recipe_Table")
    fun getAllRecipe() : Flow<List<RecipeEntity>>

    @Query("SELECT * FROM Recipe_Table")
    fun getRecipePagingSource() : PagingSource<Int, RecipeEntity>

    @Query("DELETE FROM Recipe_Table")
    suspend fun deleteAll()
}