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
package com.example.androiddevchallenge.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.NavigationActions
import java.util.Locale

@Composable
fun LoginScreen(actions: NavigationActions) {
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory())
    val emailAddress by viewModel.emailAddressState.collectAsState()
    val password by viewModel.passwordState.collectAsState()

    Surface(color = MaterialTheme.colors.background) {
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = stringResource(R.string.login_screen_background_description)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
            ) {
                Text(
                    text = stringResource(R.string.log_in).toUpperCase(Locale.ENGLISH),
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(modifier = Modifier.height(32.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    value = emailAddress,
                    placeholder = { Text(stringResource(R.string.email_hint)) },
                    textStyle = MaterialTheme.typography.body1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    onValueChange = { viewModel.updateEmailAddress(it) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    value = password,
                    placeholder = { Text(stringResource(R.string.password_hint)) },
                    textStyle = MaterialTheme.typography.body1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    onValueChange = { viewModel.updatePassword(it) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    ),
                    onClick = { actions.navigateToHome() }
                ) {
                    Text(stringResource(R.string.log_in).toUpperCase(Locale.ENGLISH))
                }

                Row(
                    modifier = Modifier.height(32.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = stringResource(R.string.dont_have_an_account))
                    Text(
                        modifier = Modifier.clickable { /*TODO*/ },
                        text = stringResource(R.string.sign_up_text),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}
