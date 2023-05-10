package com.app.alektapp.domain.model

import com.app.alektapp.domain.Entity

data class LessonBase(
    val header: LessonTittleBase,
    val text: String,
    val soundUrl: SoundUrl,
    val level: LevelBase
): Entity