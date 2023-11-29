package com.egample.kmmdemoapp.android.ui

import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument


interface AppDestination {
    val route: String
}

object ScreenInput : AppDestination {
    override val route: String = "input_name"
}

object ScreenDetail : AppDestination {
    override val route: String = "user_detail"
    const val ArgName = "user_name"
    val arguments = listOf(
        navArgument(ArgName) { type = NavType.StringType }
    )
}

fun NavController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
//        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
//            saveState = true
//        }
//        launchSingleTop = true
        restoreState = true
    }