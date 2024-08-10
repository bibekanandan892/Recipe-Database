package com.bibek.planner.domain.usecase

import com.bibek.planner.domain.model.RecipeAlarm
import com.bibek.planner.domain.repository.ScheduleRecipeRepository
import javax.inject.Inject

class GetRecipeAlarmItemUseCase @Inject constructor(private val scheduleRecipeRepository: ScheduleRecipeRepository) {

    suspend operator fun invoke(recipeId : String): RecipeAlarm? {
        return scheduleRecipeRepository.getRecipeItem(recipeId)
    }

}