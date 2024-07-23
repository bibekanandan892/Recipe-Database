package com.bibek.dashboard.data.remote.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Measures(
    @SerialName("metric")
    val metric: Metric? = null,
    @SerialName("us")
    val us: Us? = null
)