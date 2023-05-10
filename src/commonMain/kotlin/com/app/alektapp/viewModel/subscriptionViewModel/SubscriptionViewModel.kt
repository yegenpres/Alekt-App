package com.app.alektapp.viewModel.subscriptionViewModel

import com.adeo.kviewmodel.BaseSharedViewModel
import com.app.alektapp.di.AlektApp
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionStatus
import com.app.alektapp.domain.services.SubscriptionService
import io.github.aakira.napier.log


class SubscriptionViewModel(
    val service: SubscriptionService,
    ): BaseSharedViewModel<SubscriptionViewState, SubscriptionActions, SubscriptionEvents>(initialState = SubscriptionViewState()) {


        init {
            withViewModelScope {
                val status = service.isSubscribed()
                val offrs = service.fetchOffers()

                viewState = viewState.copy(
                    status = status,
                    offers = offrs.toSet(),
                    isLoading = false
                )
                log(tag = "subModel") {viewState.toString()}
            }
        }

        override fun obtainEvent(viewEvent: SubscriptionEvents) {
            when(viewEvent) {
                is SubscriptionEvents.Subscribe -> subscribe(viewEvent)
                is SubscriptionEvents.TestSubscribe -> testSubscribe()
            }
        }

    private fun subscribe(events: SubscriptionEvents.Subscribe): Unit {
        withViewModelScope {
            val status = service.subscribe(events.Context, events.product)
            viewState = viewState.copy(status = status)
        }
    }

    private fun testSubscribe(): Unit {
        if (!AlektApp.isDebug) return
            viewState = viewState.copy(status = SubscriptionStatus.IsSubscribed)
        }
    }