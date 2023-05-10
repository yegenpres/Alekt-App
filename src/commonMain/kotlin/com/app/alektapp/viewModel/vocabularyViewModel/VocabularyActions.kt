package com.app.alektapp.viewModel.vocabularyViewModel

sealed class VocabularyActions {
    data class InitialItemIndex(val index: Int): VocabularyActions()
}