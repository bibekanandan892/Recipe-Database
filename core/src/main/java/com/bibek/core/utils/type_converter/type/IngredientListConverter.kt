package com.bibek.core.utils.type_converter.type

import androidx.room.TypeConverter
import com.bibek.core.data.local.model.recipe_alarm_entity.ExtendedIngredientEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientListConverter {

    @TypeConverter
    fun fromIngredientList(ingredients: List<ExtendedIngredientEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ExtendedIngredientEntity>>() {}.type
        return gson.toJson(ingredients, type)
    }

    @TypeConverter
    fun toIngredientList(ingredientsString: String): List<ExtendedIngredientEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<ExtendedIngredientEntity>>() {}.type
        return gson.fromJson(ingredientsString, type)
    }
}