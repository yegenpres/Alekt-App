package com.app.alektapp.android.views.reading

import AnswerView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.app.alektapp.domain.model.LessonBase
import com.app.alektapp.domain.model.LevelBase
import com.app.alektapp.domain.model.SoundUndBase64
import com.app.alektapp.domain.model.WordBase
import com.app.alektapp.reading.ReadingTaskUnit
import com.app.alektapp.android.textParser.ReadingRowEntity
import com.app.alektapp.android.textParser.ReadingTaskBuilder


@Composable
fun TextExercise(
    lesson: LessonBase,
    tasks: Set<ReadingTaskUnit>,
    forTranslate: Set<WordBase>,
    showResult: (placeholder: String) -> Boolean?,
    setAnswer: (placeholder: String, answer:  String) -> Unit
) {
    fun placeholder(text: String): Placeholder {
        val width = text.split("").size * 0.8

        return Placeholder(
            width = width.em,
            height = 2.em,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    }

    var textState = remember(lesson) {
        ReadingTaskBuilder(lesson.text)
    }


    NewWordsView(
        tasks.map { it.word }.toSet(),
        Modifier.padding(vertical = 30.dp)
    )

    Card(
        Modifier.padding(vertical = 10.dp)
    ) {
        val text = buildAnnotatedString {
            textState.build.forEach { item ->
                when (item) {
                    is ReadingRowEntity.SimpleText -> append(item.text)
                    is ReadingRowEntity.AnswerField -> appendInlineContent(
                        textState.getId(item.text),
                        item.text
                    )
                    is ReadingRowEntity.TranslatableText -> appendInlineContent(
                        textState.getId(item.text),
                        item.text
                    )
                }
            }
        }

        val inlineContent = remember(lesson) {
            textState.buildInlineContent(
                answer = {
                    InlineTextContent(
                        placeholder(it)
                    ) { textWord ->
                        AnswerView(isTrue = showResult(textWord)) { answer ->
                            setAnswer(textWord, answer)
                        }
                    }
                },
                translated = {
                    InlineTextContent(
                        placeholder(it)
                    ) { textWord ->
                        TranslationView(
                            word = forTranslate.find { wordBase ->
                                wordBase.wordDK.contains(textWord)
                            } ?: WordBase(textWord, "", "","","", LevelBase.A1,SoundUndBase64(""))
                        ) {
                            it
                        }
                    }
                }
            )
        }

        Text(
            lesson.header.title,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.titleLarge
        )
        BasicText(
            text,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 1.5.em),
            inlineContent = inlineContent
        )
    }
}



