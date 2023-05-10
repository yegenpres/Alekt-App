package com.app.alektapp.android

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adeo.kviewmodel.compose.observeAsState
import com.app.alektapp.di.AlektApp
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionStatus
import com.app.alektapp.viewModel.subscriptionViewModel.SubscriptionEvents
import com.app.alektapp.android.views.SubscriptionPayWall
import com.app.alektapp.android.views.navigation.Screen
import com.app.alektapp.android.views.reading.Reading
import com.app.alektapp.android.views.vocabulary.Vocabulary

@Composable
fun AlectApp(nawController: NavHostController = rememberNavController()) {
    var selected by rememberSaveable { mutableStateOf(Screen.ReadingMain.route) }
    val pages = mapOf(
        Screen.ReadingMain to Icons.Outlined.Book,
        Screen.Vocabulary to Icons.Outlined.List
    )

   Scaffold(
            bottomBar = {
                NavigationBar(modifier = Modifier.fillMaxWidth()) {
                    for ((page, icon) in pages) run {
                        NavigationBarItem(
                            selected = page.route == selected,
                            onClick = { selected = page.route },
                            icon = {
                                Icon(icon, contentDescription = "Localized description")
                            }
                        )
                    }
                }
            },

            ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AccessManagerView(selected = selected, nawController = nawController)
            }
        }
}

@Composable
fun AccessManagerView(selected: String, nawController: NavHostController) {
    val context = LocalContext.current

    var model = AlektApp.subscriptionViewModel

    val state = model.viewStates().observeAsState()

    when (state.value.status) {
        SubscriptionStatus.IsSubscribed -> PayedContent(selected = selected, nawController = nawController)
        else -> FreeContent(
            selected = selected,
            nawController = nawController) {
            SubscriptionPayWall(
                offers = state.value.offers,
                onSubscribe = { product ->
                    model.obtainEvent(SubscriptionEvents.Subscribe(context, product))
                },
                onTest = {
                    model.obtainEvent(SubscriptionEvents.TestSubscribe)
                }
            )
        }
    }
}

@Composable
fun FreeContent(selected: String, nawController: NavHostController, paywall: @Composable () -> Unit) {
    when (selected) {
        Screen.Vocabulary.route -> Vocabulary()
        else -> paywall()
    }
}

@Composable
fun PayedContent(selected: String, nawController: NavHostController) {
    when (selected) {
        Screen.Vocabulary.route -> Vocabulary()
        Screen.ReadingMain.route -> Reading(nawController)
    }
}
