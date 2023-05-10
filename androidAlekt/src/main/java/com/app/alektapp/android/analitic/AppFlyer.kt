package com.app.alektapp.android.analitic

import android.content.Context
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.appsflyer.AFInAppEventType
import com.appsflyer.AppsFlyerLib

class AppFlyer(val key: String) {

    fun initService(context: Context) {
        AppsFlyerLib.getInstance().init(key, null, context)
        AppsFlyerLib.getInstance().start(context)
    }

    fun setDebug(isDebug: Boolean) {
        AppsFlyerLib.getInstance().setDebugLog(isDebug)
    }

    fun logIn(userID: String) {
        AppsFlyerLib.getInstance().setCustomerUserId(userID)
    }

    fun trackPurchase(context: Context, offer: SubscriptionProduct) {
        AppsFlyerLib.getInstance().logEvent(
            context,
            AFInAppEventType.PURCHASE,
            mapOf(
                "price" to offer.price,
                "title" to offer.title,
                "type" to offer.type
            )

            )
    }

}