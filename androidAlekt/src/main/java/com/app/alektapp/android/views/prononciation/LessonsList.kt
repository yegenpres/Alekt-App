package com.app.alektapp.android.views.prononciation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.alektapp.android.views.navigation.Screen
import com.app.alektapp.android.views.navigation.withLessonId

@Composable
fun LessonsList(navController: NavHostController = rememberNavController()) {
    Scaffold { padding ->
                LazyColumn(
                    modifier = Modifier.padding(padding),
                ) {
                    items(10, key = {it}) { id ->
                        ListItem(
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                                .clickable {
                                    navController.navigate(
                                        Screen.PrononciationTasks withLessonId id.toString()
                                    )                                },
                            tonalElevation = 10.dp,
                            shadowElevation = 10.dp,
                            headlineContent = { Text("title") },
                            supportingContent = { Text("theme") },
                        )

                    }
                }
    }
}