package com.app.alektapp.android.views.prononciation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.alektapp.android.media.AudioButton


@Composable
fun TestTask() {
    Card(
        modifier = Modifier.padding(vertical = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
    ) {
        Column {
            Question()
            Divider()
            Answers() {
            }
        }
    }
}

@Composable
private fun Question() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .padding(end = 30.dp)
        ,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("word")
        AudioButton(
            uri = "",
            onClick = { /*TODO*/ },
            icon = Icons.Filled.VolumeUp
        )

    }
}

@Composable
private fun Answers(onClick: (String) -> Unit) {
    val correctAnswer = "A"

    var answer by remember {
        mutableStateOf("")
    }



    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for ((index, value) in listOf("A", "B", "C").withIndex()) {
            ButtonOpt(
                onClick = {
                    answer = value
                },
                showRes = answer.isNotEmpty() ,
                isCorrect = value == correctAnswer
            ) {
                Text(value)
            }
        }
    }
}

@Composable
private fun ButtonOpt(onClick: () -> Unit, showRes: Boolean, isCorrect: Boolean, content: @Composable () -> Unit,) {
    var isPressed by remember {
        mutableStateOf(false)
    }

    var color by remember {
        mutableStateOf(Color.Black.copy(0.6f))
    }

    var colorAnimated = animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(showRes) {
        if (!showRes) return@LaunchedEffect
        if (isCorrect) {
            color = Color.Green
            return@LaunchedEffect
        }
        if (isPressed) color = Color.Red


    }

    OutlinedButton(
        border = BorderStroke(1.dp, colorAnimated.value),
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp)),
        onClick = {
            if (isPressed) return@OutlinedButton
            isPressed = true
            onClick()
        }
    ) {
        content()
    }
}
