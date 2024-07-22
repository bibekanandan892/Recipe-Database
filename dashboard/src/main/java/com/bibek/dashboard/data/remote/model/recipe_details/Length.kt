package com.bibek.dashboard.data.remote.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Length(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("unit")
    val unit: String? = null
)