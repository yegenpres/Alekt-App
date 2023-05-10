package com.app.alektapp.domain.model

import com.app.alektapp.domain.Entity

data class TaskUnit(
    val rightAnswer: AnswerBase,
    val chosenAnswer: AnswerBase?
): Entity {
    constructor(rightAnswer: AnswerBase): this(rightAnswer, null)
}


