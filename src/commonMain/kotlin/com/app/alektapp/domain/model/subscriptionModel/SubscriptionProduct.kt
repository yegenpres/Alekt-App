package com.app.alektapp.domain.model.subscriptionModel

data class SubscriptionProduct(
    val price: String,
    val title: String,
    val description: String,
    val type: PackageTypeBase
)