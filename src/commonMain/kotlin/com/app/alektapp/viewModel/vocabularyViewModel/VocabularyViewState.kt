package com.app.alektapp.viewModel.vocabularyViewModel

import com.app.alektapp.domain.log.EventLogger
import com.app.alektapp.domain.model.Languages
import com.app.alektapp.domain.model.WordBase

data class VocabularyViewState(
    val model: Set<WordBase> = setOf(),
    val searchLanguage: Languages = Languages.DK,
    val translateLanguage: Languages = Languages.Eng,
    val isSearching: Boolean = false,
    val initialIndex: Int = 0
): EventLogger("VocabularyViewState") {
    init {
        printLog()
    }
}