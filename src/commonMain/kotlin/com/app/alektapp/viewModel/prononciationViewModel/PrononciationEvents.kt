package com.app.alektapp.viewModel.prononciationViewModel

sealed interface PrononciationEvents {
    data class Test(val data: String): PrononciationEvents
}