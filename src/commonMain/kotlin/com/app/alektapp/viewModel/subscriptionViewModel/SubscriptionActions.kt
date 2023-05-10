package com.app.alektapp.viewModel.subscriptionViewModel

import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct

sealed class SubscriptionActions {
    object Subscribed: SubscriptionActions()
    data class NoSubscribed(
        val offers: Set<SubscriptionProduct> = setOf()
    ): SubscriptionActions()
    object Loading: SubscriptionActions()
    object Error: SubscriptionActions()
}