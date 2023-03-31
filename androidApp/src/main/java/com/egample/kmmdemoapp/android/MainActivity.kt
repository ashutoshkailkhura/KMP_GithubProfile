package com.egample.kmmdemoapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.egample.kmmdemoapp.android.presentation.ScreenDetail
import com.egample.kmmdemoapp.android.presentation.ScreenInput
import com.egample.kmmdemoapp.android.presentation.input_name.ScreenInputId
import com.egample.kmmdemoapp.android.presentation.navigateSingleTopTo
import com.egample.kmmdemoapp.android.presentation.theme.MyApplicationTheme
import com.egample.kmmdemoapp.android.presentation.user_detail.ScreenUserDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
) {

    val navController = rememberNavController()
//    val currentBackStack by navController.currentBackStackEntryAsState()
//    val currentDestination = currentBackStack?.destination
//    val currentScreen = ScreenInput

    Surface(modifier) {
        Scaffold(
            content = { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = ScreenInput.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(
                        route = ScreenInput.route
                    ) {
                        ScreenInputId(
                            onNameSubmit = { name ->
                                navController.navigateSingleTopTo(
                                    "${ScreenDetail.route}/$name"
                                )
                            }
                        )
                    }

                    composable(
                        route = "${ScreenDetail.route}/{${ScreenDetail.ArgName}}",
                        arguments = ScreenDetail.arguments
                    ) {
                        it.arguments?.getString(ScreenDetail.ArgName)?.let { it1 ->
                            ScreenUserDetail(
                                name = it1
                            )
                        }
                    }
                }
            }
        )
    }
}
