package com.app.alektapp.viewModel.readingViewModel

import com.adeo.kviewmodel.BaseSharedViewModel
import com.app.alektapp.domain.provider.LessonProvider
import com.app.alektapp.domain.provider.WordsProvider
import com.app.alektapp.reading.ReadingTaskService
import com.app.alektapp.reading.removeSymbols
import kotlinx.coroutines.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class ReadingViewModel(
    val lessonProvider: LessonProvider,
    val wordsProvider: WordsProvider
    ): BaseSharedViewModel<ReadingViewState, ReadingActions, ReadingEvents>(initialState = ReadingViewState()) {

    private var taskService = ReadingTaskService(null, wordsProvider)

    init {
        withViewModelScope {
            val leisOfLessons = lessonProvider.fetchListOfLesson()
            viewState = viewState.copy(titleSet = leisOfLessons)
        }
    }

    override fun obtainEvent(viewEvent: ReadingEvents) {
        when(viewEvent) {
             is ReadingEvents.Check -> checkUnswers()
             is ReadingEvents.LoadLesson ->  loadLesson(viewEvent.id)
            is ReadingEvents.SaveListIndex -> saveIndex(viewEvent.index)
            is ReadingEvents.MakeAnswer -> makeUnswer(viewEvent)
        }
    }

    private fun checkUnswers() {
        val res = taskService.isPassedSuccess()

        val wrong = taskService.failed().map { it.rightAnswer.value }.toSet()
        val passed = taskService.passed().map { it.rightAnswer.value }.toSet()

        viewState = viewState.copy(wrongAnswers = wrong, correctAnswers = passed)
    }

    private fun makeUnswer(viewEvent: ReadingEvents.MakeAnswer) {
        viewState.tasks.find { item ->
            item.task.rightAnswer.value.removeSymbols() == viewEvent.placeholder
        }?.let {
            taskService.setAnswer(it.word.id, viewEvent.value)
        }
    }

    private fun loadLesson(id: String) {
            withViewModelScope {
                if (id != viewState.item?.header?.id) {
                    viewState = viewState.copy(item = null)
                    val item = lessonProvider.fetch(id)?.let {
                        if (it.isNotEmpty()) {
                            return@let it?.first()
                        } else {
                            return@let null
                        }
                    }
                    item?.let {
                        taskService = taskService.reload(it)
                    }

                    viewState = viewState.copy(
                        item = item,
                        tasks = taskService.tasks.toSet(),
                        wordsForTranslate = taskService.wordsWithTranslate,
                        correctAnswers = setOf(),
                        wrongAnswers = setOf()
                    )
                }
            }
    }

    private fun saveIndex(index: Int) {
        viewState = viewState.copy(initialIndex = index)
    }

    fun reload(witchDuration: Duration = 0.seconds, ifEmpty: Boolean = true) = withViewModelScope {
        delay(witchDuration)

        if (viewState.titleSet.isNotEmpty() && ifEmpty) return@withViewModelScope

            val leisOfLessons = lessonProvider.fetchListOfLesson()
            viewState = ReadingViewState(titleSet = leisOfLessons)

    }
}