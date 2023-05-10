package com.app.alektapp.mok.viewModelsState

import com.app.alektapp.domain.model.subscriptionModel.PackageTypeBase
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.app.alektapp.mok.Mock
import com.app.alektapp.viewModel.subscriptionViewModel.SubscriptionViewState

fun Mock.subscriptionViewState() = SubscriptionViewState(
    offers = setOf(
        SubscriptionProduct(title = "product month", description = "description", price = "10$", type = PackageTypeBase.MONTHLY),
        SubscriptionProduct(title = "product anual", description = "description", price = "100$", type = PackageTypeBase.ANNUAL)
    )
)