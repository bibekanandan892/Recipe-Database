package com.bibek.dashboard.data.remote.model.search.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("image")
    val image: String? = null,
    @SerialName("imageType")
    val imageType: String? = null,
    @SerialName("title")
    val title: String? = null
)