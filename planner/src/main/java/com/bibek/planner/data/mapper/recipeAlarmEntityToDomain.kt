package com.bibek.planner.data.mapper

import com.bibek.core.data.local.model.recipe_alarm_entity.ExtendedIngredientEntity
import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.core.utils.formatTo12Hour
import com.bibek.planner.data.model.recipe_details.ExtendedIngredient
import com.bibek.planner.domain.model.RecipeAlarm

fun RecipeAlarmEntity.toDomain(): RecipeAlarm {
    return RecipeAlarm(
        name = this.name,
        image = this.image,
        recipeId = this.recipeId,
        alarmId = id,
        time = formatTo12Hour(hours = hour?:0, minutes = minute?:0),
        dayOfWeek = dayOfWeek,
        ingredients = ingredients.map { it.toDomain() }
    )
}

fun ExtendedIngredientEntity.toDomain(): ExtendedIngredient {
    return ExtendedIngredient(
        nameClean = this.nameClean,
        originalName = this.originalName,
    )
}

