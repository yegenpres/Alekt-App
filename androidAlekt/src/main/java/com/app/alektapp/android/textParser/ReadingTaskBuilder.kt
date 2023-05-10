package com.app.alektapp.android.textParser

import androidx.compose.foundation.text.InlineTextContent
import com.app.alektapp.reading.removeSymbols
import com.app.alektapp.reading.textToLIstBranches
import java.util.*


fun String.extractInlineItem(): String = "${this.replace(Regex("[^a-zA-Z]"), "")}"
fun String.generateUUID(): String =  this + UUID.randomUUID().toString()


data class ReadingTaskBuilder(
    private val text: String,
) {
    private var idList = mutableMapOf<String, String>()


    fun getId(word: String): String {
        return idList[word.trim()]!!
    }

    val build: List<ReadingRowEntity> = text.textToLIstBranches().map {
        val value = it.removeSymbols().trim()
        when {
            it.startsWith("{{") -> {
                idList[value] = value.generateUUID()
                ReadingRowEntity.AnswerField(value)
            }
            it.startsWith("{") -> {
                idList[value] = value.generateUUID()
                ReadingRowEntity.TranslatableText(value)
            }
            else -> ReadingRowEntity.SimpleText("$it ")
        }
    }

    fun buildInlineContent(
        answer:  (String)-> InlineTextContent,
        translated:  (String)-> InlineTextContent
    ): Map<String, InlineTextContent> {
        return build
            .filter { it !is ReadingRowEntity.SimpleText }
            .associateBy({
                idList[it.text]!!
            }, { item ->
                when (item) {
                    is ReadingRowEntity.AnswerField -> answer(item.text)
                    is ReadingRowEntity.TranslatableText -> translated(item.text)
                    else -> translated(item.text)
                }
            })
    }
}

sealed class ReadingRowEntity(val text: String) {
    class SimpleText(text: String): ReadingRowEntity(text)
    class AnswerField(text: String): ReadingRowEntity(text)
    class TranslatableText(text: String): ReadingRowEntity(text)
}