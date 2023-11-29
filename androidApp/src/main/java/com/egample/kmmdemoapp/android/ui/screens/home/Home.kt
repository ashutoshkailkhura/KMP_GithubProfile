package com.egample.kmmdemoapp.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.egample.kmmdemoapp.android.ui.components.ItemHistoryText

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onNameSubmit: (name: String) -> Unit,
) {
    InputScreen(modifier, onNameSubmit)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    onNameSubmit: (name: String) -> Unit,
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

//        TODO use local db to retrieve data

        LazyColumn() {
            item {
                ItemHistoryText(label = "ashutoshkailkhura") { onNameSubmit(it) }
            }
            item {
                ItemHistoryText(label = "skydoves") { onNameSubmit(it) }
            }
            item {
                ItemHistoryText(label = "kunal-kushwaha") { onNameSubmit(it) }
            }
            item {
                ItemHistoryText(label = "SebastianAigner") { onNameSubmit(it) }
            }
        }
    }
}
