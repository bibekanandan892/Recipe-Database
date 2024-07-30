package com.bibek.dashboard.domain.usecase

import com.bibek.core.data.local.model.recipe_alarm_entity.RecipeAlarmEntity
import com.bibek.dashboard.domain.repository.RecipeRepository
import javax.inject.Inject

class SaveRecipeAlarmUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipeAlarmEntity: RecipeAlarmEntity) {
        return recipeRepository.saveRecipeAlarm(recipeAlarmEntity)
    }
}