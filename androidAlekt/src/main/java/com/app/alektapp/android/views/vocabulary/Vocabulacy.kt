package com.app.alektapp.android.views.vocabulary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.app.alektapp.di.AlektApp
import com.app.alektapp.viewModel.vocabularyViewModel.VocabularyEvents
import com.app.alektapp.android.views.utils.rememberLazyScrollState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vocabulary() {
    val model = AlektApp.vocaublaryViewModel
    val state = model.viewStates().observeAsState()

    val scrollState = rememberLazyScrollState(state.value.initialIndex) {
        model.obtainEvent(VocabularyEvents.SaveListIndex(it))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchWordField(
                        searchLang = state.value.searchLanguage,
                        onSearch = { model.obtainEvent(VocabularyEvents.Find(it)) },
                        changeLanguage = {model.obtainEvent(VocabularyEvents.SetSearchLanguage(it))}
                        )
                    },
                actions = {
                    OutlinedButton(onClick = { model.obtainEvent(VocabularyEvents.ChangeTranslateLang)}) {
                        Text(state.value.translateLanguage.name)
                    }
                }
            )
        }
    ) { padding ->
            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    state = scrollState
                ) {
                    if (state.value.model.isEmpty()) {
                        item {
                            Box(Modifier.height(400.dp)) {
                                CircularProgressIndicator(
                                    Modifier
                                        .fillMaxSize()
                                        .padding(40.dp)
                                )
                            }
                        }
                    }
                    items(state.value.model.size, key = { it }) { index ->
                        WordView(
                            word = state.value.model.elementAt(index),
                            lang = state.value.translateLanguage
                        )
                    }
                }

            }
        }
}
