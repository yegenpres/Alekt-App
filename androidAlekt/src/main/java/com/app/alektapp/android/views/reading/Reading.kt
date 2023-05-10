package com.app.alektapp.android.views.reading

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adeo.kviewmodel.compose.observeAsState
import com.app.alektapp.android.views.navigation.Screen
import com.app.alektapp.di.AlektApp
import com.app.alektapp.viewModel.readingViewModel.ReadingEvents
import com.app.alektapp.android.views.utils.rememberLazyScrollState

@Composable
fun Reading(navController: NavHostController) {
    var model = AlektApp.readingViewModel

        val state = model.viewStates().observeAsState()

        NavHost(
            navController = navController,
            startDestination = Screen.ReadingMain.route
        ) {
            composable(Screen.ReadingMain.route) {
                ReadingList(
                    navController,
                    listState = rememberLazyScrollState(state.value.initialIndex) {
                        model.obtainEvent(ReadingEvents.SaveListIndex(it))
                    },
                    listOfLessons = state.value.titleSet
                ) {
                    model.obtainEvent(ReadingEvents.LoadLesson(it))
                    navController.navigate(Screen.ReadingTask.route)
                }
            }
            composable(Screen.ReadingTask.route) {
                ReadingTask(
                    navController,
                    state.value,
                    onLoad = {   },
                    checkExercise = {
                        model.obtainEvent(ReadingEvents.Check)
                    },
                    showResult = { placeholder ->
                        when {
                            state.value.wrongAnswers.contains(placeholder) -> false
                            state.value.correctAnswers.contains(placeholder) -> true
                            else -> null
                        }
                    }
                ) { placeholder, answer ->
                    model.obtainEvent(ReadingEvents.MakeAnswer(
                        placeholder,
                        answer
                    ))
                }
            }
        }
}