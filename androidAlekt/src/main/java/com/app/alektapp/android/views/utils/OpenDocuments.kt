package com.app.alektapp.android.views.utils

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import com.app.alektapp.Documents


@Composable
fun OpenDocument(doc: Documents) {
    val uriHandler = LocalUriHandler.current
    Text(doc.title, color = Color.Blue, modifier = Modifier.clickable {
        uriHandler.openUri(doc.url)
    })
}
