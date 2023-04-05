package com.egample.kmmdemoapp.android.presentation.input_name

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenInputId(
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
        verticalArrangement = Arrangement.Center,
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
            label = { Text(text = "Name") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Button(
            shape = RoundedCornerShape(12.dp),
            onClick = {
                onNameSubmit(inputText.text)
            },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "CHECKOUT")
        }
    }
}
