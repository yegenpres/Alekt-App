package com.app.alektapp.android.views.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun rememberLazyScrollState(initialIndex: Int, onSave: (Int) -> Unit): LazyListState {
    val scrollState = rememberLazyListState(initialIndex)

    DisposableEffect(true) {
        onDispose {
            onSave(scrollState.firstVisibleItemIndex)
        }
    }

    return scrollState
}