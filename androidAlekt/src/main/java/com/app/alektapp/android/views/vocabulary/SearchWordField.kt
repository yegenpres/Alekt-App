package com.app.alektapp.android.views.vocabulary

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.alektapp.domain.model.Languages

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWordField(
    searchLang: Languages,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    changeLanguage: (Languages) -> Unit
    ) {
    var value by remember {
        mutableStateOf("")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

            SearchBar(
                modifier = modifier
                    .padding(end = 40.dp)
                    .fillMaxWidth(),
                query = value,
                onQueryChange = { value = it },
                onSearch = { onSearch(it) },
                active = false,
                onActiveChange = { it },
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { isExpanded = !isExpanded}) {
                        Text(searchLang.name.lowercase())
                    }
                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("ENG") },
                            onClick = { changeLanguage(Languages.Eng)},
                        )
                        DropdownMenuItem(
                            text = { Text("DK") },
                            onClick = { changeLanguage(Languages.DK)},
                            )
                        DropdownMenuItem(
                            text = { Text("RU") },
                            onClick = { changeLanguage(Languages.RU)},
                           )
                    }
                               },
            ) {

            }

}