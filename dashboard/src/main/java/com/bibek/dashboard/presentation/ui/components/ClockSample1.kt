package com.bibek.dashboard.presentation.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ClockDialog(closeSelection: () -> Unit,onTimeSelect : (Int,Int)-> Unit) {
    DateTimeDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        selection = DateTimeSelection.Time { newTime ->
            onTimeSelect(newTime.hour, newTime.minute)
        }
    )
}