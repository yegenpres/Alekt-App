package com.app.alektapp.viewModel.vocabularyViewModel

import com.app.alektapp.domain.log.EventLogger
import com.app.alektapp.domain.model.Languages

sealed class VocabularyEvents(eventName: String): EventLogger(tag = "VocabularyEvents$eventName") {
    data class Find(val query: String): VocabularyEvents("Find") {
        init {
            printLog()
        }
    }
    data class SetSearchLanguage(val lang: Languages): VocabularyEvents("SetSearchLanguage") {
        init {
            printLog()
        }
    }
    data class SaveListIndex(val index: Int): VocabularyEvents("SaveListIndex") {
        init {
            printLog()
        }
    }
    object ChangeTranslateLang: VocabularyEvents("ChangeTranslateLang") {
        init {
            printLog()
        }
    }
    object FetchNext: VocabularyEvents("FetchNext") {
        init {
            printLog()
        }
    }
    object FetchPrevious: VocabularyEvents("FetchPrevious") {
        init {
            printLog()
        }
    }
}