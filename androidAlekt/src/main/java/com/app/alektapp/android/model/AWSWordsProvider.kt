package com.app.alektapp.android.model

import android.util.Log

import com.app.alektapp.domain.model.*
import com.app.alektapp.domain.provider.WordsProvider
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Page
import com.amplifyframework.core.model.query.QueryOptions
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.Word
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun Word.toBase(): WordBase = WordBase(
    id,
    wordDk,
    "",
    translateEng,
    translateRu,
    level = level.toBase(),
    SoundUndBase64("data:audio/mp3;base64,${this.soundPath}")
)

class AWSWordsProvider: WordsProvider() {
    override suspend fun fetch(id: String): Set<WordBase> = query {
        Where.identifier(Word::class.java, id)
    }

    override suspend fun fetch(start: Int, end: Int): Set<WordBase> = query {
    Where.matchesAll()
    .paginated(Page.startingAt(start).withLimit(end))
}


    override suspend fun fetch(): Set<WordBase> = query {
        Where.matchesAll()
    }

    override suspend fun fetchMatchDK(wordDk: String, start: Int, end: Int): Set<WordBase> = query {
        Where.matches(Word.WORD_DK.contains(wordDk)).paginated(Page.startingAt(start).withLimit(end))
    }

    override suspend fun fetchMatchENG(english: String, start: Int, end: Int): Set<WordBase> = query {
        Where.matches(Word.TRANSLATE_ENG.contains(english)).paginated(Page.startingAt(start).withLimit(end))
    }

    override suspend fun fetchMatchRU(Ru: String, start: Int, end: Int): Set<WordBase> = query {
        Where.matches(Word.TRANSLATE_RU.contains(Ru)).paginated(Page.startingAt(start).withLimit(end))
    }

    override suspend fun fetchMatchDK(wordDk: String): Set<WordBase> = query {
        Where.matches(Word.WORD_DK.contains(wordDk))
    }

    override suspend fun fetchMatchENG(english: String): Set<WordBase> = query {
        Where.matches(Word.TRANSLATE_ENG.contains(english))
    }

    override suspend fun fetchMatchRU(Ru: String): Set<WordBase> = query {
        Where.matches(Word.TRANSLATE_RU.contains(Ru))
    }

    override suspend fun fetchMedia(id: String): SoundUndBase64 = suspendCancellableCoroutine { cor ->
        Amplify.DataStore.query(
            Word::class.java,
            Where.identifier(Word::class.java, id),
            { items ->
                while (items.hasNext()) {
                    cor.resume(SoundUndBase64(items.next().soundPath))
                }
            },
            { failure ->
                Log.e("Word", "Could not query DataStore", failure)
                cor.resumeWithException(failure)
            }
        )
    }

    override suspend fun fetchEqualDK(wordDk: String): Set<WordBase> = query {
        Where.matches(Word.WORD_DK.eq(wordDk))
    }

}


private suspend fun query(filter: () -> QueryOptions): Set<WordBase> = suspendCancellableCoroutine { cor ->
    Amplify.DataStore.query(
        Word::class.java,
        filter(),
        { items ->
            val result = mutableListOf<WordBase>()
            while (items.hasNext()) {
                result.add(items.next().toBase())
            }

            cor.resume(result.toSet())
        },
        { failure ->
            Log.e("Word", "Could not query DataStore", failure)
            cor.resumeWithException(failure)
        }
    )
}

