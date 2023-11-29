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
import com.egample.kmmdemoapp.android.ui.ScreenDetail
import com.egample.kmmdemoapp.android.ui.ScreenInput
import com.egample.kmmdemoapp.android.ui.screens.home.Home
import com.egample.kmmdemoapp.android.ui.navigateSingleTopTo
import com.egample.kmmdemoapp.android.ui.theme.MyApplicationTheme
import com.egample.kmmdemoapp.android.ui.screens.detail.ScreenUserDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {

    val navController = rememberNavController()

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
                    Home(
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
