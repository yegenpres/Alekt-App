package com.app.alektapp.android.views.prononciation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.amplifyframework.storage.StorageAccessLevel
import com.amplifyframework.storage.options.StorageDownloadFileOptions


@Composable
fun PrononcTaskScreen(lessonId: String?) {
    val uri = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"


    val options = StorageDownloadFileOptions.builder()
        .accessLevel(StorageAccessLevel.PUBLIC)
        .build()





    val context = LocalContext.current
    val videoItem = MediaItem.fromUri(uri)

    val player = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            this.prepare()
            this.addMediaItem(videoItem)
        }
    }


    var lifecycle by remember {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
            DisposableEffect(
                AndroidView(
                factory = { context ->
                    PlayerView(context).also {
                        it.player = player
                    }
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .aspectRatio(16 / 9f),
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
        Spacer(Modifier.height(30.dp))
    Text("Choose the correct sound",
        style = MaterialTheme.typography.bodyLarge
        )
        TestTask()
        TestTask()
        TestTask()
        TestTask()
    }
}
