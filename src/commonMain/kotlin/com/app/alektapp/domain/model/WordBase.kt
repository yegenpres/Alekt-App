package com.app.alektapp.domain.model

data class WordBase(
    val id: String,
    val wordDK: String,
    val transcription: String,
    val translateEng: String,
    val translateRu: String,
    val level: LevelBase,
    val audio: SoundUndBase64
) : VocabularyEntity {
    constructor(testInt: Int): this(
        id = "id$testInt",
        wordDK = "word$testInt",
        transcription = "transcription",
        translateEng = "translate",
        translateRu = "translateRu",
        level = LevelBase.A1,
        audio = SoundUndBase64(""),
    )
}