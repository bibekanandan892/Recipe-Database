package com.bibek.dashboard.domain.repository

import androidx.paging.PagingData
import com.bibek.dashboard.data.model.scarch.res.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipe(
        query: String,
        cuisine: String,
        diet: String,
        sort: String
    ): Flow<PagingData<Recipe>>
}






