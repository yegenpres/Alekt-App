package com.app.alektapp.domain.model.subscriptionModel

enum class PaywallBenefits {
    Reading,
    Vocabulary,
    Listening,
    Exercises;

    override fun toString(): String = when(this) {
        Reading -> "Read text by Danish."
        Vocabulary -> "Gain own vocabulary for learning"
        Listening -> "Listen texts and Danish words."
        Exercises -> "Make exercises for improve Your skills."
    }
}