package com.app.alektapp.viewModel.readingViewModel

import com.app.alektapp.domain.log.EventLogger
import com.app.alektapp.domain.model.LessonBase
import com.app.alektapp.domain.model.LessonTittleBase
import com.app.alektapp.domain.model.WordBase
import com.app.alektapp.reading.ReadingTaskUnit

data class ReadingViewState(
    val titleSet: Set<LessonTittleBase> = setOf(),
    val item: LessonBase? = null,
    val tasks: Set<ReadingTaskUnit> = setOf(),
    val wordsForTranslate: Set<WordBase> = setOf(),
    val correctAnswers: Set<String> = setOf(),
    val wrongAnswers: Set<String> = setOf(),
    val initialIndex: Int = 0
    ): EventLogger("ReadingViewState") {
    init {
        printLog()
    }
}