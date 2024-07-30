package com.bibek.core.data.local.model.recipe_alarm_entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bibek.core.utils.type_converter.type.IngredientListConverter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "Recipe_Alarm_Table")
@Serializable
@TypeConverters(IngredientListConverter::class)
data class RecipeAlarmEntity(
    @SerialName("id")
    @PrimaryKey
    val id: Int? = null,
    val hour : Int?= null,
    val min : Int? = null,
    val dayOfWeek: Int? = null,
    val title: String = "",
    val image: String = "",
    val ingredients: List<ExtendedIngredientEntity> = listOf()
)