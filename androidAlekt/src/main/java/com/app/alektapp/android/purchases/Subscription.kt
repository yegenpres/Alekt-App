package com.app.alektapp.android.purchases

import android.content.Context
import com.app.alektapp.android.R
import com.app.alektapp.android.analitic.AppFlyer
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionStatus
import com.app.alektapp.domain.services.SubscriptionService
import com.revenuecat.purchases.CacheFetchPolicy
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.LogLevel
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.PurchaseParams
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.interfaces.LogInCallback
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback
import com.revenuecat.purchases.purchaseWith
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Subscription(val context: Context):
    SubscriptionService {

    private val appFluer = AppFlyer(context.getString(R.string.AppFlyer))

    private val key = context.getString(R.string.RevenueKey)

    private var products: List<Package> = listOf()

    override fun setDebug(isDebug: Boolean) {
        Purchases.logLevel =  if (isDebug) LogLevel.DEBUG else LogLevel.ERROR

        appFluer.setDebug(isDebug)
    }

    override fun init() {
        Purchases.configure(PurchasesConfiguration.Builder(context, key).build())

        appFluer.initService(context)
    }

    override fun init(userId: String) {
        Purchases.configure(PurchasesConfiguration.Builder(context, key).appUserID(userId).build())

        appFluer.initService(context)
    }

    override suspend fun logIn(userId: String): Boolean = suspendCoroutine { con ->
        Purchases.sharedInstance.logIn(userId, object : LogInCallback {
            override fun onError(error: PurchasesError) = con.resume(false)

            override fun onReceived(customerInfo: CustomerInfo, created: Boolean) = con.resume(true)

        })

        appFluer.logIn(userId)
    }

    override suspend fun isSubscribed(): SubscriptionStatus = suspendCoroutine { con ->
        Purchases.sharedInstance.getCustomerInfo(CacheFetchPolicy.default(), object : ReceiveCustomerInfoCallback {
            override fun onError(error: PurchasesError) = con.resume(SubscriptionStatus.Err(error.message))

            override fun onReceived(customerInfo: CustomerInfo) {
                with (customerInfo.entitlements.active) {
                    when {
                        isNotEmpty() -> con.resume(SubscriptionStatus.IsSubscribed)
                        isEmpty() -> con.resume(SubscriptionStatus.NotSubscribed)
                        else -> con.resume(SubscriptionStatus.Err(""))
                    }
                }
            }
        })
    }

    override suspend fun fetchOffers(): Set<SubscriptionProduct> = suspendCoroutine { cor ->
        Purchases.sharedInstance.getOfferingsWith({ error ->
            cor.resume(setOf())
        }) { offerings ->
            offerings.current?.availablePackages?.let {
                    offers ->
                products = offers
                offers.map { it.toBase() }.run {
                    cor.resume(this.toSet())
                }
            }
        }
    }

    override suspend fun subscribe(context: Any, productBase: SubscriptionProduct): SubscriptionStatus = suspendCoroutine { cor ->
        products.find { it.product.title == productBase.title }?.let {
            (context as Context).findActivity()?.let { activity ->
                Purchases.sharedInstance.purchaseWith(
                    PurchaseParams.Builder(activity, it).build(),
                onSuccess = { _, _ ->
                    cor.resume(SubscriptionStatus.IsSubscribed)

                    appFluer.trackPurchase(context, productBase)
                    },
                    onError = { err, _ ->
                        cor.resume(SubscriptionStatus.Err(err.message))
                    }
                )
            }
        }
    }
}

