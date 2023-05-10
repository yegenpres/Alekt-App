package tests.subscription

import android.content.Context
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.app.alektapp.android.purchases.Subscription
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionStatus
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@SmallTest
class SunsccriptionTest {
        lateinit var service: Subscription
        lateinit var context: Context

    private fun runTest(call: suspend ()-> Unit) {
        runBlocking {
            launch {
                call()
            }
        }
    }

        @Before
        fun initService() {
            context = InstrumentationRegistry.getInstrumentation().targetContext
            service = Subscription(context = context)
            service.init()
        }

        @Test
        fun subscriptionTest_fetchOffers() = runTest {
            val offers = service.fetchOffers()
            assert(offers.size == 1)
        }


        @Test
        fun subscriptionTest_MakePurchase() = runTest {
        val offers = service.fetchOffers()
            if (offers.isEmpty()) {
                assert(false)
            }

            val result = service.subscribe(context, offers.first())

            when(result) {
                is SubscriptionStatus.Err -> assert(false)
                SubscriptionStatus.IsSubscribed -> assert(true)
                SubscriptionStatus.NoInternet -> assert(false)
                SubscriptionStatus.NotSubscribed -> assert(false)
            }
        }
    }