package com.app.alektapp.viewModel.prononciationViewModel
//
//import com.adeo.kviewmodel.BaseSharedViewModel
//import com.app.alekt_app.domain.Action
//
//class PrononciationViewModel(): BaseSharedViewModel<PrononciationBase, Action, PrononciationEvents>(initialState = PrononciationBase()) {
//    init {
////        withViewModelScope {
////            appState.flow
////                .map {
////                    it.prononciation
////                }
////                .collect {
////                    viewState = it
////                }
////
////        }
//    }
//
//    override fun obtainEvent(viewEvent: PrononciationEvents) {
//        when(viewEvent) {
//            is PrononciationEvents.Test -> test(viewEvent.data)
//        }
//    }
//
//
//
////    private fun signIn(value: String) {
////        viewState = viewState.copy(subscription = viewState.subscription.copy(isSubscribed = true))
////        viewAction = SingIn(userId = value)
////    }
//
//    private fun test(data: String) {
//    }
//}