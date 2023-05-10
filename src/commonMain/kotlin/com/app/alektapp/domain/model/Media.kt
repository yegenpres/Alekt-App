package com.app.alektapp.domain.model

import com.app.alektapp.domain.Entity

interface Media: Entity {
    val value: String
}

data class SoundUrl( override val value: String): Media

data class VideoUrl( override val value: String): Media

data class SoundUndBase64( override val value: String): Media

