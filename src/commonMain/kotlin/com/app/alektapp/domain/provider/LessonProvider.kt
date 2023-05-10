package com.app.alektapp.domain.provider

import com.app.alektapp.domain.model.LessonBase
import com.app.alektapp.domain.model.LessonTittleBase
import com.app.alektapp.domain.model.Media


abstract class LessonProvider: Provider<LessonBase> {
    abstract override suspend fun fetch(id: String): Set<LessonBase>

    abstract override suspend fun fetchMedia(id: String): Media

    abstract suspend fun fetchListOfLesson(): Set<LessonTittleBase>
}