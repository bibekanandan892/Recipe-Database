package com.bibek.planner.data.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("localizedName")
    val localizedName: String? = null,
    @SerialName("name")
    val name: String? = null
)