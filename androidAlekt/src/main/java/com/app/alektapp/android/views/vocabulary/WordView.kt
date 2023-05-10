package com.app.alektapp.android.views.vocabulary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.alektapp.domain.model.Languages
import com.app.alektapp.domain.model.WordBase
import com.app.alektapp.android.media.AudioButton

@Composable
fun WordView(word: WordBase, lang: Languages) {
    ListItem(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {
            }
        ,
        tonalElevation = 10.dp,
        shadowElevation = 10.dp,
        overlineContent = { Text(word.transcription) },
        headlineContent = { Text(word.wordDK) },
        supportingContent = {
            Text(
            when(lang) {
                Languages.DK -> word.translateEng
                Languages.RU -> word.translateRu
                Languages.Eng -> word.translateEng
            })
                            },
        trailingContent = {
            AudioButton(
                uri = word.audio.value,
                icon = Icons.Default.VolumeUp
            )
        }
        )
}