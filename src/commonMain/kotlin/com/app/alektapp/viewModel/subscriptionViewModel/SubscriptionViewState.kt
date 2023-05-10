package com.app.alektapp.viewModel.subscriptionViewModel

import com.app.alektapp.domain.log.EventLogger
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionStatus

data class SubscriptionViewState(
    val status: SubscriptionStatus = SubscriptionStatus.IsSubscribed,
    val offers: Set<SubscriptionProduct> = setOf(),
    val isLoading: Boolean = false
): EventLogger("SubscriptionViewState") {
    init {
        printLog()
    }
}