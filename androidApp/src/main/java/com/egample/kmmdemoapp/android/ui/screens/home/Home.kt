package com.egample.kmmdemoapp.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.egample.kmmdemoapp.android.MainViewModel
import com.egample.kmmdemoapp.android.ui.components.ItemHistoryText

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onNameSubmit: (name: String) -> Unit,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState = viewModel.uiStateHomeScreen.collectAsState()
    InputScreen(modifier, onNameSubmit, uiState.value.history) {
        viewModel.clearHistory()
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    onNameSubmit: (name: String) -> Unit,
    history: List<String>,
    clearHistory: () -> Unit
) {
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inputText,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "personIcon"
                )
            },
            onValueChange = {
                inputText = it
            },
            label = { Text(text = "Github User Name") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onNameSubmit(inputText.text)
                }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        if (history.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Chip(
                    onClick = { clearHistory() },
                    border = ChipDefaults.outlinedBorder,
                    colors = ChipDefaults.outlinedChipColors(),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Localized description"
                        )
                    }
                ) {
                    Text("Clear")
                }
            }
        }

        LazyColumn() {
            items(history) {
                ItemHistoryText(label = it) { onNameSubmit(it) }
            }
            item {
                ItemHistoryText(label = "skydoves") { onNameSubmit(it) }
            }
//            todo
            item {
                ItemHistoryText(label = "kunal-kushwaha") { onNameSubmit(it) }
            }
            item {
                ItemHistoryText(label = "SebastianAigner") { onNameSubmit(it) }
            }
        }
    }
}
