package com.app.alektapp.viewModel.subscriptionViewModel

import com.app.alektapp.domain.log.EventLogger
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct

sealed class SubscriptionEvents(eventName: String): EventLogger("SubscriptionEvent$eventName") {
    data class Subscribe(
        val Context: Any,
        val product: SubscriptionProduct
        ): SubscriptionEvents("Subscribe") {
            init {
                printLog()
            }
        }

    object TestSubscribe: SubscriptionEvents("TestSubscribe") {
        init {
            printLog()
        }
    }
}