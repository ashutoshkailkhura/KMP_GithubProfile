package com.egample.kmmdemoapp.android.presentation.user_detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.egample.kmmdemoapp.android.MainViewModel
import com.egample.kmmdemoapp.android.data.UserDetail

@Composable
fun ScreenUserDetail(
    name: String,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState = viewModel.uiStateUserDetail.collectAsState()
    viewModel.getDetail(name)
    SetUserDetail(detail = uiState.value.detail)
}

@Composable
fun SetUserDetail(
    detail: UserDetail?
) {
    detail?.let {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                AsyncImage(
                    model = detail.img,
                    contentDescription = null,
                    modifier = Modifier
                        .size(184.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )
            }
            item {
                Text(
                    text = detail.name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                )
            }
        }
    } ?: run { ErrorMsg(error = "Not Found") }
}

@Composable
fun ErrorMsg(error: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error.orEmpty(),
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Red
            ),
        )
    }
}
