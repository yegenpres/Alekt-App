package com.app.alektapp.viewModel.readingViewModel

import com.app.alektapp.domain.log.EventLogger

sealed class ReadingEvents(eventName: String): EventLogger(tag = "ReadingEvent$eventName") {
    object Check: ReadingEvents("Check") {
        init {
            printLog()
        }
    }
    data class LoadLesson(val id: String): ReadingEvents("LoadLesson") {
        init {
            printLog()
        }
    }

    data class MakeAnswer(val placeholder: String, var value: String): ReadingEvents("MakeAnswer") {
        init {
            value = value.lowercase()
            printLog()
        }
    }

    data class SaveListIndex(val index: Int): ReadingEvents("SaveListIndex") {
        init {
            printLog()
        }
    }
}