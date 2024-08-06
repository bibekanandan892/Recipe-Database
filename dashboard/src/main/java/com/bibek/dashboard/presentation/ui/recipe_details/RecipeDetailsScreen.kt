package com.bibek.dashboard.presentation.ui.recipe_details

import android.graphics.drawable.BitmapDrawable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.bibek.core.ui.components.IngredientsItem
import com.bibek.core.ui.components.TopBar
import com.bibek.core.utils.alarm.scheduleWeeklyAlarm
import com.bibek.dashboard.presentation.ui.components.Button
import com.bibek.dashboard.presentation.ui.components.RecipeRow
import com.bibek.dashboard.presentation.ui.components.ReminderContent
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeDetailsScreen(
    uiState: RecipeDetailsState,
    onEvent: (RecipeDetailsEvent) -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val painter = rememberAsyncImagePainter(uiState.image)
    ModalBottomSheetLayout(sheetShape = RoundedCornerShape(
        topEnd = 20.dp,
        topStart = 20.dp
    ),
        sheetState = bottomSheetState,
        sheetContent = {
            ReminderContent(selectedTime = uiState.selectedTime,
                isShowClock = uiState.isShowClock, onTimeSelect = { hour, min ->
                    onEvent.invoke(RecipeDetailsEvent.OnTimeSelect(hour, min))
                }, closeSelection = {
                    onEvent(RecipeDetailsEvent.OnCloseClock)
                }, onSaveClick = {
                    val painterState = painter.state
                    if (painterState is AsyncImagePainter.State.Success) {
                        // Extract the bitmap from the painter's result
                        val imageBitmap = (painterState.result.drawable as BitmapDrawable).bitmap
                        val alarmId = UUID.randomUUID().hashCode()
                        onEvent(
                            RecipeDetailsEvent.SetRecipeAlarm(
                                image = imageBitmap,
                                alarmId = alarmId
                            )
                        )
                        scheduleWeeklyAlarm(
                            context = context,
                            alarmId = alarmId,
                            dayOfWeek = uiState.selectedDay?.dayOfWeek ?: Calendar.SUNDAY,
                            hour = uiState.hour,
                            minute = uiState.minute,
                            recipeId = uiState.id.toString(),
                            isRepeat = true
                        )
                        scope.launch {
                            bottomSheetState.hide()
                        }
                    }
                }, onDayClick = {
                    onEvent(RecipeDetailsEvent.OnDaySelect(it))
                }, onTimeClick = { onEvent(RecipeDetailsEvent.OnTimeClick) },
                onCloseSheet = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                }, selectedDay = uiState.selectedDay
            )
        }
    ) {
        RecipeDetailsUI(
            painter = painter,
            uiState = uiState,
            onEvent = onEvent,
            onAddRecipeClick = {
                scope.launch {
                    bottomSheetState.show()
                }
            })
    }
}

@Composable
private fun RecipeDetailsUI(
    uiState: RecipeDetailsState,
    painter: AsyncImagePainter,
    onEvent: (RecipeDetailsEvent) -> Unit,
    onAddRecipeClick: () -> Unit
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp), topBar = {
        TopBar("Recipe Details",
            onBackClick = { onEvent(RecipeDetailsEvent.NavigateBack) })
    }, floatingActionButton = {
        if (uiState.name.isNotEmpty() && uiState.ingredients.isNotEmpty()) {
            Button(title = "Add Recipe", onClick = onAddRecipeClick)
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (uiState.isRecipeDetailsLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.Black)
                }
            } else if (uiState.ingredients.isNotEmpty()) {
                RecipeDetailsContent(uiState = uiState, painter = painter)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No Recipe Details Found")
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipeDetailsContent(uiState: RecipeDetailsState, painter: AsyncImagePainter) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn {
            item {
                RecipeRow(title = uiState.name, painter = painter )
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    ),
                    text = "Ingredients",
                    fontSize = TextUnit(6f, TextUnitType.Em),
                    style = TextStyle(fontFamily = FontFamily.SansSerif),
                    fontWeight = FontWeight.Bold
                )
            }
            items(uiState.ingredients.size) {
                val item = uiState.ingredients[it]
                IngredientsItem(
                    nameClean = item.nameClean ?: return@items,
                    originalName = item.originalName ?: return@items
                )
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun RecipeScreenPreview() {
    RecipeDetailsScreen(uiState = RecipeDetailsState())
}