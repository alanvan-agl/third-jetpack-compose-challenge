package com.example.androiddevchallenge.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.AppDestinations.HOME_NAVIGATION_ROUTE
import com.example.androiddevchallenge.ui.AppDestinations.LOGIN_ROUTE
import com.example.androiddevchallenge.ui.AppDestinations.WELCOME_ROUTE
import com.example.androiddevchallenge.ui.MainDestinations.HOME_ROUTE
import java.util.*

object AppDestinations {
    const val WELCOME_ROUTE = "welcome"
    const val LOGIN_ROUTE = "login"
    const val HOME_NAVIGATION_ROUTE = "home_navigation"
}

@Composable
fun WelcomeNavigation() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController) }
    NavHost(navController = navController, startDestination = WELCOME_ROUTE) {
        composable(WELCOME_ROUTE) {
            WelcomeScreen(actions)
        }
        composable(LOGIN_ROUTE) {
            LoginScreen(actions)
        }
        composable(HOME_NAVIGATION_ROUTE) {
            HomeNavigation(navController)
        }
    }
}

class NavigationActions(navController: NavController) {
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val navigateToLoginScreen: () -> Unit = {
        navController.navigate(LOGIN_ROUTE)
    }
    val navigateToHome: () -> Unit = {
        navController.navigate(HOME_NAVIGATION_ROUTE)
    }
}
