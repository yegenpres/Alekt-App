package com.app.alektapp.domain.provider

import com.app.alektapp.domain.model.SoundUndBase64
import com.app.alektapp.domain.model.WordBase

abstract class WordsProvider: Provider<WordBase> {
    abstract override suspend fun fetch(id: String): Set<WordBase>
    abstract override suspend fun fetchMedia(id: String): SoundUndBase64
    abstract suspend fun fetchEqualDK(wordDk: String): Set<WordBase>
    abstract suspend fun fetchMatchDK(wordDk: String, start: Int, end: Int): Set<WordBase>
     abstract suspend fun fetchMatchENG(english: String, start: Int, end: Int): Set<WordBase>
     abstract suspend fun fetchMatchRU(Ru: String, start: Int, end: Int): Set<WordBase>
    abstract suspend fun fetchMatchDK(wordDk: String): Set<WordBase>
    abstract suspend fun fetchMatchENG(english: String): Set<WordBase>
    abstract suspend fun fetchMatchRU(Ru: String): Set<WordBase>
     abstract suspend fun fetch(start: Int, end: Int): Set<WordBase>
    abstract suspend fun fetch(): Set<WordBase>
}