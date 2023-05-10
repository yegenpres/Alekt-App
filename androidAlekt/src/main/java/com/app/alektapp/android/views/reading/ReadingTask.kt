package com.app.alektapp.android.views.reading
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.alektapp.viewModel.readingViewModel.ReadingViewState


@Composable
fun ReadingTask(
    navController: NavHostController,
    state: ReadingViewState?,
    onLoad: () -> Unit,
    checkExercise: () -> Unit,
    showResult: (placeholder: String) -> Boolean?,
    setAnswer: (placeholder: String, answer: String) -> Unit,
) {

    LaunchedEffect(state) {
        onLoad()
    }

    if (state?.item == null) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.End
        ) {
            SoundTextPlayer(lesson = state.item!!)
            Spacer(Modifier.height(30.dp))
            TextExercise(
                state.item!!,
                state.tasks,
                state.wordsForTranslate,
                showResult = showResult,
                setAnswer = setAnswer
            )
            ElevatedButton(
                modifier = Modifier.padding(8.dp),
                onClick = {
                checkExercise()
            }) {
                Text("Tjek")
            }
        }
    }
}

