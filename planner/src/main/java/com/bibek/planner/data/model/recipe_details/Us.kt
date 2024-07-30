package com.bibek.planner.data.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Us(
    @SerialName("amount")
    val amount: Double? = null,
    @SerialName("unitLong")
    val unitLong: String? = null,
    @SerialName("unitShort")
    val unitShort: String? = null
)