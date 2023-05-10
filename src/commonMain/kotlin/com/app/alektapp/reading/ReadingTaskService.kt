package com.app.alektapp.reading

import com.app.alektapp.domain.model.AnswerBase
import com.app.alektapp.domain.model.LessonBase
import com.app.alektapp.domain.model.TaskUnit
import com.app.alektapp.domain.model.WordBase
import com.app.alektapp.domain.provider.WordsProvider
import com.app.alektapp.domain.services.TaskService
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.log


fun String.removeSymbols(): String {
    val regex = Regex("[{}.,!?«»]")
    return regex.replace(this, "").trim()
}

class ParceTextForReadingView(val text: String) {
    fun build(): List<String> = text.textToLIstBranches()
}

fun String.textToLIstBranches(): List<String> {
    var result = mutableListOf<String>()

    var branch = ""

    this.split("[\\s\t]+".toRegex()).forEach() {

        fun endWith(s: String): Boolean {
            return s.endsWith("}")
                    || s.endsWith("}.")
                    || s.endsWith("}!")
                    || s.endsWith("}\"")
                    || s.endsWith("},")
                    || s.endsWith("}?")
                    || s.endsWith("} ")
                    || s.endsWith("}    ")
                    || s.endsWith("}?«")
                    || s.endsWith("}?»")

        }

        if (it.startsWith("{") && endWith(it)) {
            result.add(it)
        } else if (it.startsWith("{") && !endWith(it)) {
            branch = it
        } else if (!it.startsWith("{") && endWith(it)) {
            branch = "$branch $it"
            result.add(branch)
            branch = ""
        } else if (!it.startsWith("{") && !endWith(it)) {
            if (branch.isNotEmpty()) {
                branch = "$branch $it"
            } else {
                result.add(it)
            }

        }
    }
    return result
}

fun String.extractWords(selector: String): List<String> = this.textToLIstBranches().filter {
    it.startsWith(selector)
}.toList()


class ReadingTaskService(
    private val lesson: LessonBase?,
    private val wordsProvider: WordsProvider,
): TaskService {
    private val taskPrefix = "{{"
    private val translateblePrefix = "{"

    var tasks: MutableList<ReadingTaskUnit> = mutableListOf()
    var wordsWithTranslate: MutableSet<WordBase> = mutableSetOf()

    private suspend fun loadData() {
        val wordKeys = lesson?.text?.extractWords(translateblePrefix)?.toSet() ?: setOf()
            wordKeys.forEach {key ->
                when {
                    key.startsWith(taskPrefix) -> makeTask(key)?.let { tasks.add(it) }
                    key.startsWith(translateblePrefix) -> fetchWord(key)?.let { wordsWithTranslate.add(it) }
                    else -> {}
                }
            }
    }
    private suspend fun makeTask(word: String): ReadingTaskUnit? {
        fetchWord(word)?.let {
           return ReadingTaskUnit(
                it,
                task = TaskUnit(
                    rightAnswer = AnswerBase(word.removeSymbols())
                )
            )
        }
        return null
    }

    private suspend fun fetchWord(word: String): WordBase? {
        val words = wordsProvider.fetchEqualDK(word.removeSymbols())
        return if (words.isNotEmpty()) {
            words.find { it.wordDK == word.removeSymbols() }
        } else {
            log (
                priority = LogLevel.ERROR,
                tag = "error") { "$word did not fetched from server!!!!!! ReadingTaskService 104" }
            null
        }
    }


    fun setAnswer(id: String, value: String) =
        tasks.indexOfFirst { it.word.id == id }?.let {
            tasks[it].task = tasks[it].task.copy(chosenAnswer = AnswerBase(value))
        }


    suspend fun reload(lesson: LessonBase): ReadingTaskService {
        val newIns = ReadingTaskService(lesson, wordsProvider)
        newIns.loadData()
        return newIns
    }

    override fun isPassedSuccess(): Boolean =
        tasks.all {
            with(it.task) {
                rightAnswer == chosenAnswer
            }
        }


    override fun passed(): Set<TaskUnit> = tasks.filter {
        with(it.task) {
           rightAnswer == chosenAnswer
        }
    }.map {
        it.task
    }.toSet()

    override fun failed(): Set<TaskUnit> = tasks.filter {
        with(it.task) {
            rightAnswer != chosenAnswer
        }
    }.map {
        it.task
    }.toSet()
}