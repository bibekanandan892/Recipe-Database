package com.bibek.planner.domain.usecase

import com.bibek.planner.domain.model.RecipeAlarm
import com.bibek.planner.domain.repository.ScheduleRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeAlarmListUseCase @Inject constructor(private val scheduleRecipeRepository: ScheduleRecipeRepository) {

    operator fun invoke(): Flow<List<RecipeAlarm>> {
        return scheduleRecipeRepository.getRecipeAlarmList()
    }
}