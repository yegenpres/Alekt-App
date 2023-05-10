package com.app.alektapp.domain.provider

import com.app.alektapp.domain.Entity
import com.app.alektapp.domain.model.Media

interface Provider<T : Entity> {
    suspend fun fetch(id: String): Set<T>
    suspend fun fetchMedia(id: String): Media
}