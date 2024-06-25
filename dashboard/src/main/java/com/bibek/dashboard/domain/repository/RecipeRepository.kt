package com.bibek.dashboard.domain.repository

import com.bibek.core.utils.NetworkResult
import com.bibek.dashboard.data.model.scarch.res.RecipeSearchResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipe(
        query: String,
        cuisine: String,
        diet: String,
        sort: String
    ): Flow<NetworkResult<RecipeSearchResponse>>
}