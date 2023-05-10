package com.app.alektapp.domain.model

import com.app.alektapp.domain.Entity

data class LessonTittleBase(
    val id: String,
    val title: String,
    val subTitle: String,
): Entity