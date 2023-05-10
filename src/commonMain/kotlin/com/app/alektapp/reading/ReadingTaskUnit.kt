package com.app.alektapp.reading

import com.app.alektapp.domain.model.TaskUnit
import com.app.alektapp.domain.model.WordBase

data class ReadingTaskUnit(val word: WordBase, var task: TaskUnit)