package com.app.alektapp.android.views.reading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.alektapp.domain.model.WordBase
import com.app.alektapp.android.media.AudioButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun TranslationView(word: WordBase, onClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    var detailIsShowen by remember {
        mutableStateOf(false)
    }

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .clickable {
                    detailIsShowen = true
                    scope.launch {
                        delay(2.seconds)
                        detailIsShowen = false
                    }
                },

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                word.wordDK,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            AudioButton(
                uri = word.audio.value,
                onClick = { /*TODO*/ },
                icon = Icons.Outlined.VolumeUp
            )
        }

        WordDetail(
            Modifier
                .offset(y = (-25).dp)
                .align(Alignment.Center)
                .fillMaxHeight(),
            word = word,
            isShowen = detailIsShowen
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WordDetail(modifier: Modifier, word: WordBase, isShowen: Boolean) {
    AnimatedVisibility(
        visible = isShowen,
        modifier = modifier,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column() {
            Badge(Modifier.padding(4.dp)) {
                Text(word.translateRu)

            }
            Badge(Modifier.padding(4.dp)) {
                Text(word.translateEng)
            }
        }
    }
}