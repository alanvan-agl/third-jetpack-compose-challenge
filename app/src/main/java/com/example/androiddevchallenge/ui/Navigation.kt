/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.di.SingletonInjector
import com.example.androiddevchallenge.ui.Destinations.HOME_ROUTE
import com.example.androiddevchallenge.ui.Destinations.LOGIN_ROUTE
import com.example.androiddevchallenge.ui.Destinations.WELCOME_ROUTE
import com.example.androiddevchallenge.ui.home.HomeScreen
import com.example.androiddevchallenge.ui.welcome.LoginScreen
import com.example.androiddevchallenge.ui.welcome.WelcomeScreen

object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val LOGIN_ROUTE = "login"
    const val HOME_ROUTE = "home_route"
    const val PROFILE_ROUTE = "profile_route"
}

@Composable
fun NavContainer(singletonInjector: SingletonInjector) {
    val navController = rememberNavController()
    val actions = remember(navController) { NavigationActions(navController) }
    NavHost(navController = navController, startDestination = WELCOME_ROUTE) {
        composable(WELCOME_ROUTE) {
            WelcomeScreen(actions)
        }
        composable(LOGIN_ROUTE) {
            LoginScreen(actions)
        }
        composable(HOME_ROUTE) {
            HomeScreen(singletonInjector, actions, navController)
        }
    }
}

class NavigationActions(navController: NavController) {
    val navigateToLoginScreen: () -> Unit = {
        navController.navigate(LOGIN_ROUTE)
    }
    val navigateToHome: () -> Unit = {
        navController.navigate(HOME_ROUTE) {
            launchSingleTop = true
        }
    }
    val navigateToProfile: () -> Unit = {
        // TODO: navigate to profile screen
    }
}
