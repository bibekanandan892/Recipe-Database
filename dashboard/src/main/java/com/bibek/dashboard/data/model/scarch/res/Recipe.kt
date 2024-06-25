package com.bibek.dashboard.data.model.scarch.res


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("imageType")
    val imageType: String? = null,
    @SerialName("title")
    val title: String? = null
)