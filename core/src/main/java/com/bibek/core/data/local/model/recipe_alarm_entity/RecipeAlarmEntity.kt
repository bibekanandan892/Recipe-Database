package com.bibek.core.data.local.model.recipe_alarm_entity


import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bibek.core.utils.type_converter.type.BitmapTypeConverter
import com.bibek.core.utils.type_converter.type.IngredientListConverter
import kotlinx.serialization.SerialName

@Entity(tableName = "Recipe_Alarm_Table")
@TypeConverters(IngredientListConverter::class,BitmapTypeConverter::class)
data class RecipeAlarmEntity(
    @SerialName("id")
    @PrimaryKey
    val id: Int? = 0,
    val hour : Int?= null,
    val minute : Int? = null,
    val dayOfWeek: Int? = null,
    val name: String = "",
    val image: Bitmap? = null,
    val ingredients: List<ExtendedIngredientEntity> = listOf(),
    val recipeId : String = "",
    val isRepeat : Boolean = false
)