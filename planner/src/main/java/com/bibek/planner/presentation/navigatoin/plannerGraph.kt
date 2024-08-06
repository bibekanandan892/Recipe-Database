package com.bibek.planner.presentation.navigatoin

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.bibek.core.utils.navigation.Destination
import com.bibek.planner.presentation.ui.recipe_alarm_details_screen.RecipeAlarmDetailsEvent
import com.bibek.planner.presentation.ui.recipe_alarm_details_screen.RecipeAlarmDetailsScreen
import com.bibek.planner.presentation.ui.recipe_alarm_details_screen.RecipeAlarmDetailsViewModel
import com.bibek.planner.presentation.ui.schedule_recipe_alarm.ScheduleRecipeScreen
import com.bibek.planner.presentation.ui.schedule_recipe_alarm.ScheduleRecipeAlarmViewModel

fun NavGraphBuilder.plannerGraph() {
    composable(route = Destination.SCHEDULE_RECIPE.name) {
        val scheduleRecipeAlarmViewModel: ScheduleRecipeAlarmViewModel = hiltViewModel()
        val uiState by scheduleRecipeAlarmViewModel.uiState.collectAsState()
        ScheduleRecipeScreen(uiState = uiState, onEvent = scheduleRecipeAlarmViewModel::onEvent)
    }
    composable(
        route = Destination.RECIPE_ALARM_DETAILS.name + "/{recipeId}",
        arguments = listOf(navArgument("recipeId") { type = NavType.StringType }),
        deepLinks = listOf(navDeepLink {
            uriPattern = "app://com.bibek.recipedatabase/${Destination.RECIPE_ALARM_DETAILS.name}/{recipeId}"
        })
    ) { backStackEntry ->
        val recipeId = backStackEntry.arguments?.getString("recipeId").toString()
        val recipeAlarmDetailsViewModel: RecipeAlarmDetailsViewModel = hiltViewModel()
        val uiState by recipeAlarmDetailsViewModel.uiState.collectAsState()
        LaunchedEffect(key1 = true) {
            recipeAlarmDetailsViewModel.onEvent(RecipeAlarmDetailsEvent.GetRecipeAlarm(recipeId = recipeId))
        }
        RecipeAlarmDetailsScreen(uiState = uiState)
    }


}
