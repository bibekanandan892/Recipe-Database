package com.bibek.dashboard.data.model.scarch.res


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "Recipe_Table")
@Serializable
data class Recipe(
    @PrimaryKey
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("image")
    val image: String? = null,
    @SerialName("imageType")
    val imageType: String? = null,
    @SerialName("title")
    val title: String? = null
)