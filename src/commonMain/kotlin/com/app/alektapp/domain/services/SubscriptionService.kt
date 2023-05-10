package com.app.alektapp.domain.services

import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionStatus

interface SubscriptionService {
        fun setDebug(isDebug: Boolean): Unit
        fun init(): Unit
        fun init(userId: String): Unit
        suspend fun logIn(userId: String): Boolean
        suspend fun isSubscribed(): SubscriptionStatus
        suspend fun fetchOffers(): Set<SubscriptionProduct>
        suspend fun subscribe(context: Any, productBase: SubscriptionProduct): SubscriptionStatus
    }
