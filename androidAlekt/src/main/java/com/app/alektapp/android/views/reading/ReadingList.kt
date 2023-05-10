package com.app.alektapp.android.views.reading

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.alektapp.domain.model.LessonTittleBase

@Composable
fun ReadingList(
    navController: NavHostController = rememberNavController(),
    listState: LazyListState = rememberLazyListState(),
    listOfLessons:  Set<LessonTittleBase>,
    onClick: (String) -> Unit
) {
    Scaffold { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(padding),
        ) {
            if (listOfLessons.isEmpty()) {
                item {
                    Box(Modifier.height(400.dp)) {
                        CircularProgressIndicator(
                            Modifier
                                .fillMaxSize()
                                .padding(40.dp)
                        )
                    }
                }
            }
            items(listOfLessons.size, key = { it }) { index ->
                val currentItem = listOfLessons.elementAt(index)

                ListItem(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .clickable {
                            onClick(currentItem.id)
                        },
                    tonalElevation = 10.dp,
                    shadowElevation = 10.dp,
                    headlineContent = { Text(currentItem.title) },
                    supportingContent = { Text(currentItem.subTitle) },
                )

            }
        }
    }
}