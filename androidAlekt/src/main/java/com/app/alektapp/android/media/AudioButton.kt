package com.app.alektapp.android.media

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext


@Composable
fun AudioButton(uri: String, onClick: () -> Unit, icon: ImageVector) {
    val ctx = LocalContext.current
    val mediaPlayer = remember  { MediaPlayer() }
    var isSetUp by remember {
        mutableStateOf(false)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }


    IconButton(onClick = {
        with(mediaPlayer) {
            if (!isSetUp) {
                try {
                    setDataSource(uri)
                    prepare()
                    isSetUp = true
                } catch(e: Error) {
                    Toast.makeText(ctx, "Can not load sound", Toast.LENGTH_SHORT).show()
                }

            }
            if (isPlaying) {
                pause()
            } else {
                start()
            }
            onClick()
        }
    }) {
        Icon(icon, contentDescription = null)
    }
}