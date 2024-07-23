package com.bibek.dashboard.data.mapper

import com.bibek.dashboard.data.local.model.search.RecipeEntity
import com.bibek.dashboard.data.remote.model.search.response.RecipeDto

fun RecipeDto.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        image = image,
        imageType = imageType,
        title = title)
}