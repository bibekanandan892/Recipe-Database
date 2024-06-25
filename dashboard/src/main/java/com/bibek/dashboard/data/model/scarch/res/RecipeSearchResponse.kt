package com.bibek.dashboard.data.model.scarch.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("offset")
    val offset: Int? = null,
    @SerialName("results")
    val recipes: List<Recipe?>? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
)