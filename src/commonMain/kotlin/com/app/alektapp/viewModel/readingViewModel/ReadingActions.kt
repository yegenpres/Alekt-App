package com.app.alektapp.viewModel.readingViewModel

sealed class ReadingActions {
    data class Result(val correct: Set<String>, val wrong: Set<String>): ReadingActions()
}