package com.app.alektapp.domain.provider

import com.app.alektapp.domain.model.UsersVocabularyBase

interface UserVocabularyProvider: Provider<UsersVocabularyBase> {
    override suspend fun fetch(id: String): Set<UsersVocabularyBase>
}