package com.bibek.dashboard.data.mapper

import com.bibek.core.data.local.model.recipe_alarm_entity.ExtendedIngredientEntity
import com.bibek.dashboard.data.remote.model.recipe_details.ExtendedIngredient

fun ExtendedIngredient.toIngredientsEntity(): ExtendedIngredientEntity {
    return ExtendedIngredientEntity(
            id = this.id,
            aisle = this.aisle,
            image = this.image,
            consistency = this.consistency,
            name = this.name,
            original = this.original,
            originalName = this.originalName,
            amount = this.amount,
            unit = this.unit,
            meta = this.meta,

    )
}
