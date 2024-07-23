package com.bibek.dashboard.data.remote.model.search.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class
RecipeSearchDto(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("offset")
    val offset: Int? = null,
    @SerialName("results")
    val recipeDtos: List<RecipeDto?>? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
)