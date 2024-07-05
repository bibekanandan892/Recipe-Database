package com.bibek.dashboard.data.local.model.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe_Table")
data class RecipeEntity(
    @PrimaryKey
    val id: Int? = 0,
    val image: String? = null,
    val imageType: String? = null,
    val title: String? = null
)
