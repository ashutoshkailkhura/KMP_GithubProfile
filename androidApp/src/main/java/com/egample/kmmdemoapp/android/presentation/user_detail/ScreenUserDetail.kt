package com.egample.kmmdemoapp.android.presentation.user_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    viewModel.getDetail(name)
    val uiState = viewModel.uiStateUserDetail.collectAsState()
    SetUserDetail(detail = uiState.value.detail)
}

@Composable
fun SetUserDetail(
    detail: UserDetail?
) {
    detail?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = detail.img,
                                contentDescription = "User image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(184.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = detail.id.toString(),
                                fontSize = 36.sp,
                                color = Color.LightGray,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.TopEnd)
                            )
                        }
                    }
                    item {
                        Text(
                            text = detail.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                        )
                    }

                    item {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Location icon",
                                tint = Color.LightGray,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = detail.location,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                    }

                    item {
                        Text(
                            text = detail.bio,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmailRow(mail: String) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Location icon",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = mail,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
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
