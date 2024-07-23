package com.bibek.dashboard.data.remote.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Step(
    @SerialName("equipment")
    val equipment: List<Equipment?>? = null,
    @SerialName("ingredients")
    val ingredients: List<Ingredient?>? = null,
    @SerialName("length")
    val length: Length? = null,
    @SerialName("number")
    val number: Int? = null,
    @SerialName("step")
    val step: String? = null
)