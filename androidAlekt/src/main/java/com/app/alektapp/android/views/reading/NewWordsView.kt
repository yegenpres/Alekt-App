package com.app.alektapp.android.views.reading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.alektapp.android.media.AudioButton
import com.app.alektapp.domain.model.WordBase

@Composable
 fun NewWordsView(words: Set<WordBase>, modifier: Modifier) {
    Card(modifier) {
        words.forEach { word ->
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(word.wordDK)
                Text(word.translateEng)
                AudioButton(uri= word.audio.value, onClick = { /*TODO*/ }, icon = Icons.Default.VolumeUp)
            }
            Divider()
        }
    }
}