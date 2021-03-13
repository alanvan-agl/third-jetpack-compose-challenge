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
import com.example.androiddevchallenge.ui.Destinations.HOME_ROUTE
import com.example.androiddevchallenge.ui.Destinations.LOGIN_ROUTE
import com.example.androiddevchallenge.ui.Destinations.WELCOME_ROUTE
import java.util.*

object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home"
    const val PROFILE_ROUTE = "profile"
}

@Composable
fun Navigation(darkTheme: Boolean, navController: NavHostController) {
    val actions = remember(navController) { NavigationActions(navController) }
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                listOf(HOME_ROUTE, Destinations.PROFILE_ROUTE).forEach { route ->
                    BottomNavigationItem(
                        icon = {
                            if (route == HOME_ROUTE) {
                                Icon(Icons.Filled.Search, stringResource(R.string.search_icon))
                            } else Icon(
                                Icons.Filled.AccountCircle,
                                stringResource(R.string.account_circle)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.home).toUpperCase(Locale.ENGLISH),
                                style = MaterialTheme.typography.caption
                            )
                        },
                        selected = currentRoute == route,
                        onClick = {
                            if (route == HOME_ROUTE) {
                                navController.navigate(route) {
                                    popUpTo = navController.graph.startDestination
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = HOME_ROUTE) {
            composable(WELCOME_ROUTE) {
                WelcomeScreen(darkTheme, actions)
            }
            composable(LOGIN_ROUTE) {
                LoginScreen(darkTheme, actions)
            }
            composable(HOME_ROUTE) {
                HomeScreen(darkTheme, actions)
            }
        }
    }
}

class NavigationActions(navController: NavController) {

}