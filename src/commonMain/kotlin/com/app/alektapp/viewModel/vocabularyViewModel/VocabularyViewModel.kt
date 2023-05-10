package com.app.alektapp.viewModel.vocabularyViewModel

import com.adeo.kviewmodel.BaseSharedViewModel
import com.app.alektapp.domain.model.Languages
import com.app.alektapp.domain.provider.WordsProvider
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


class VocabularyViewModel(private val provider: WordsProvider): BaseSharedViewModel<VocabularyViewState, VocabularyActions, VocabularyEvents>(initialState = VocabularyViewState(isSearching = true)) {

    private val portion = 6
    private var currentOffet = portion

    init {
        withViewModelScope {
            val leisOfLessons = provider.fetch()
            viewState = viewState.copy(isSearching = false, model = leisOfLessons)
        }
    }

    override fun obtainEvent(viewEvent: VocabularyEvents) {
        when(viewEvent) {
            is VocabularyEvents.Find -> find(viewEvent.query)
            is VocabularyEvents.SetSearchLanguage -> setSearchLanguage(viewEvent.lang)
            is VocabularyEvents.SaveListIndex -> saveIndex(viewEvent.index)
            VocabularyEvents.ChangeTranslateLang -> changeTranslateLanguage()
            VocabularyEvents.FetchNext -> loadNext()
            VocabularyEvents.FetchPrevious -> loadPrevious()
        }
    }

    private fun find(value: String) = withViewModelScope {
        viewState = viewState.copy(isSearching = true)
        val leisOfLessons = when (viewState.searchLanguage) {
            Languages.Eng -> {
                provider.fetchMatchENG(value.lowercase())
            }
            Languages.DK -> {
                provider.fetchMatchDK(value.lowercase())
            }
            Languages.RU -> {
                provider.fetchMatchRU(value.lowercase())
            }
        }

        viewState = viewState.copy(isSearching = false, model = leisOfLessons)
    }



    private fun setSearchLanguage(lang: Languages): Unit {
        viewState = viewState.copy(searchLanguage = lang)
    }

    private fun changeTranslateLanguage(): Unit {
        viewState = if (viewState.translateLanguage == Languages.Eng) {
            viewState.copy(translateLanguage = Languages.RU)
        } else {
            viewState.copy(translateLanguage = Languages.Eng)
        }
    }

    private fun loadPrevious() = withViewModelScope {

        }


    private fun loadNext() = withViewModelScope {

    }

    private fun saveIndex(index: Int) {
        viewState = viewState.copy(initialIndex = index)
    }

    fun reload(witchDuration: Duration = 0.seconds, ifEmpty: Boolean = true) = withViewModelScope {
        delay(witchDuration)

        if (viewState.model.isNotEmpty() && ifEmpty) return@withViewModelScope

        val leisOfLessons = provider.fetch()
        viewState = viewState.copy(isSearching = false, model = leisOfLessons)
    }
}