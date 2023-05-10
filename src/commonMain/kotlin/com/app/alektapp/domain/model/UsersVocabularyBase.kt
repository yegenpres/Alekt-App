package com.app.alektapp.domain.model

data class UsersVocabularyBase(
    val words: Set<WordBase>
) : VocabularyEntity {
    constructor() : this(setOf())
}
