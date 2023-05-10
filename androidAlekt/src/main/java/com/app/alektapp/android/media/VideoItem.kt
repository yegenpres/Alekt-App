package com.app.alektapp.android.media


import android.net.Uri
import androidx.media3.common.MediaItem


data class VideoItem(
    val videoUri: Uri,
    val mediaItem: MediaItem,
    val name: String,
    )