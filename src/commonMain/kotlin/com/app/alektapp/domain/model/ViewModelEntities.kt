package com.app.alektapp.domain.model

import com.app.alektapp.domain.Entity

sealed interface ViewModelEntities: Entity

interface LessonEntity: ViewModelEntities

interface VocabularyEntity: ViewModelEntities

interface ReadingEntity: ViewModelEntities
