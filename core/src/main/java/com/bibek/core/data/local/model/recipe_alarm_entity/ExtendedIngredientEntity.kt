package com.bibek.core.data.local.model.recipe_alarm_entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredientEntity(
    @SerialName("aisle")
    val aisle: String? = null,
    @SerialName("amount")
    val amount: Double? = null,
    @SerialName("consistency")
    val consistency: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("meta")
    val meta: List<String?>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nameClean")
    val nameClean: String? = null,
    @SerialName("original")
    val original: String? = null,
    @SerialName("originalName")
    val originalName: String? = null,
    @SerialName("unit")
    val unit: String? = null
)