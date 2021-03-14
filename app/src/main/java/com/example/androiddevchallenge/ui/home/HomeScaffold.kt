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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.Destinations.HOME_ROUTE
import com.example.androiddevchallenge.ui.Destinations.PROFILE_ROUTE
import com.example.androiddevchallenge.ui.NavigationActions
import com.example.androiddevchallenge.ui.theme.bottomNavigation
import java.util.Locale

@Composable
fun HomeScaffold(
    actions: NavigationActions,
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(56.dp),
                backgroundColor = MaterialTheme.colors.background,
                elevation = bottomNavigation
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Spa, stringResource(R.string.spa_icon)) },
                    label = {
                        Text(
                            text = stringResource(R.string.home).toUpperCase(Locale.ENGLISH),
                            style = MaterialTheme.typography.caption
                        )
                    },
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    selected = currentRoute == HOME_ROUTE,
                    onClick = { actions.navigateToHome() }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            stringResource(R.string.account_icon)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.profile).toUpperCase(Locale.ENGLISH),
                            style = MaterialTheme.typography.caption
                        )
                    },
                    selected = currentRoute == PROFILE_ROUTE,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    onClick = { actions.navigateToProfile() }
                )
            }
        },
        floatingActionButton = {
            Button(
                shape = RoundedCornerShape(50),
                modifier = Modifier.size(56.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = stringResource(R.string.play_button)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        content = content
    )
}
