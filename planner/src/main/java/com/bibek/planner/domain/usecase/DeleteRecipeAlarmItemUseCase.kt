package com.bibek.planner.domain.usecase

import com.bibek.planner.domain.repository.ScheduleRecipeRepository
import javax.inject.Inject

class DeleteRecipeAlarmItemUseCase @Inject constructor(private val scheduleRecipeRepository: ScheduleRecipeRepository) {

    suspend operator fun invoke(alarm : Int){
        return scheduleRecipeRepository.deleteRecipeAlarmById(alarm)
    }
}