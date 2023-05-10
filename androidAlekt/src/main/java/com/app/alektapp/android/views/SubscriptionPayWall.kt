package com.app.alektapp.android.views

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.pointer.pointerInput
import com.app.alektapp.Documents
import com.app.alektapp.domain.model.subscriptionModel.SubscriptionProduct
import com.app.alektapp.domain.model.subscriptionModel.PaywallBenefits
import com.app.alektapp.android.views.utils.OpenDocument

@Composable
fun SubscriptionPayWall(offers: Set<SubscriptionProduct>, onSubscribe: (SubscriptionProduct) -> Unit, onTest: () -> Unit) {
    Scaffold() {
        Column(
            Modifier
                .padding(it)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Benefits()
            for (offer in offers) {
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .clickable { onSubscribe(offer)}

                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { onTest() },
                                onTap = { onSubscribe(offer) }
                            )
                        }
                        .padding(10.dp),
                    tonalElevation = 15.dp,
                    shadowElevation = 15.dp,
                    headlineContent = { Text(text = offer.title) },
                    supportingContent = { Text(text = offer.description) },
                    trailingContent = { Text(text = offer.price) }
                )
            }
            Docks()
        }
    }
}


@Composable
private fun Benefits() {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        for (benefit in PaywallBenefits.values() ) {
         Punkt(
             Modifier.padding(vertical = 15.dp),
             text = benefit)
        }
    }
}

@Composable
private fun Punkt(mod: Modifier = Modifier, text: PaywallBenefits) {
    Row(mod) {
        Icon(
            Icons.Default.CheckCircle,
            modifier = Modifier.padding(horizontal = 20.dp),
            contentDescription = null
        )
        Text(text.toString())
    }
}

@Composable
private fun Docks() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OpenDocument(Documents.TERMS_OF_USAGE)
        OpenDocument(Documents.PRIVACY_POLICY)
    }
}
