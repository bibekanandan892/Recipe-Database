package com.bibek.dashboard.data.remote.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metric(
    @SerialName("amount")
    val amount: Double? = null,
    @SerialName("unitLong")
    val unitLong: String? = null,
    @SerialName("unitShort")
    val unitShort: String? = null
)