package com.bibek.dashboard.data.remote.model.recipe_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalyzedInstruction(
    @SerialName("name")
    val name: String? = null,
    @SerialName("steps")
    val steps: List<Step?>? = null
)