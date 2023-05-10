package com.app.alektapp.domain.model.subscriptionModel

sealed class SubscriptionStatus {
    object IsSubscribed: SubscriptionStatus()
    object NotSubscribed: SubscriptionStatus()
    data class Err(val err: String): SubscriptionStatus()
    object NoInternet: SubscriptionStatus()
}