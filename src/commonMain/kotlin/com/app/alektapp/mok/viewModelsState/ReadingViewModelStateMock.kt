package com.app.alektapp.mok.viewModelsState

import com.app.alektapp.mok.Mock
import com.app.alektapp.mok.model.lessonBase
import com.app.alektapp.viewModel.readingViewModel.ReadingViewState

fun Mock.readingViewState() =  ReadingViewState(
    titleSet = setOf(this.lessonBase(1).header),
    item = this.lessonBase(1)
)

