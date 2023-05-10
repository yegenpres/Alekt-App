package com.app.alektapp.android.purchases
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.app.alektapp.domain.model.subscriptionModel.PackageTypeBase
import com.revenuecat.purchases.Package
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.revenuecat.purchases.PackageType

fun Package.toBase() = SubscriptionProduct(
    title = this.product.title,
    price = this.product.price.formatted,
    description = this.product.description,
    type = when(this.packageType) {
        PackageType.UNKNOWN -> PackageTypeBase.UNKNOWN
        PackageType.CUSTOM -> PackageTypeBase.CUSTOM
        PackageType.LIFETIME -> PackageTypeBase.LIFETIME
        PackageType.ANNUAL -> PackageTypeBase.ANNUAL
        PackageType.SIX_MONTH -> PackageTypeBase.SIX_MONTH
        PackageType.THREE_MONTH -> PackageTypeBase.THREE_MONTH
        PackageType.TWO_MONTH -> PackageTypeBase.TWO_MONTH
        PackageType.MONTHLY -> PackageTypeBase.MONTHLY
        PackageType.WEEKLY -> PackageTypeBase.WEEKLY
    }
)

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}