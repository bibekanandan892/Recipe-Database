package com.bibek.dashboard.presentation.ui.components

import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

@Composable
internal fun ClockDialog(closeSelection: () -> Unit,onTimeSelect : (Int,Int)-> Unit) {
    ClockDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        selection = ClockSelection.HoursMinutes(withButtonView = true) { hours, minutes ->
            onTimeSelect(hours,minutes)
        },
        config = ClockConfig(
            is24HourFormat = true
        )
    )
}