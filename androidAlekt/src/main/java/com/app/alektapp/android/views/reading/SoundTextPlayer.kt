package com.app.alektapp.android.views.reading

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.app.alektapp.domain.model.LessonBase

@Composable
fun SoundTextPlayer(lesson: LessonBase) {
    val clipShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                moveTo(0f, size.height / 3)
                lineTo(size.width, size.height / 3)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            return Outline.Generic(path)
        }
    }

    println(lesson.soundUrl.value)

    LaunchedEffect(lesson) {

    }

    val context = LocalContext.current

    val videoItem = MediaItem.fromUri(lesson.soundUrl.value)

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            this.setMediaItem(videoItem)
        }
    }

    LaunchedEffect(lesson) {
        player.removeMediaItem(0)
        player.setMediaItem(videoItem)
    }

    var lifecycle by remember(lesson) {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    DisposableEffect(
        AndroidView(
            factory = { _ ->
                PlayerView(context).also {
                    it.player = player
                }
            },
            modifier = Modifier
                .padding(top = 10.dp)
                .aspectRatio(16 / 9f)
                .clip(clipShape)
            ,
            update = {
                when (lifecycle) {
                    Lifecycle.Event.ON_CREATE -> {

                    }
                    Lifecycle.Event.ON_PAUSE -> {
                        it.onPause()
                        it.player?.pause()
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        it.onResume()
                    }
                    else -> Unit
                }
            }
        )) {
        onDispose {
            player.release()

        }
    }
}