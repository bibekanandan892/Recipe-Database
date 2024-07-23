package com.bibek.dashboard.data.mapper

import com.bibek.dashboard.data.local.model.search.RecipeEntity
import com.bibek.dashboard.domain.model.search.response.Recipe

fun RecipeEntity.toRecipe() : Recipe{
    return Recipe(
        id = id?:0,
        title = "Name ${title?.take(20)}...",
        image = image?:""
    )
}