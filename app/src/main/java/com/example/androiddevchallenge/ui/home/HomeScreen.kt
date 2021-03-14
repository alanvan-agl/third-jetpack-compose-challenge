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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.di.SingletonInjector
import com.example.androiddevchallenge.ui.NavigationActions
import java.util.Locale

@Composable
fun HomeScreen(
    singletonInjector: SingletonInjector,
    actions: NavigationActions,
    navController: NavHostController
) {
    val viewModel: HomeViewModel =
        viewModel(factory = HomeViewModelFactory(singletonInjector.provideHomeRepository()))
    HomeScaffold(actions, navController) {
        val searchKeyword by viewModel.searchKeyword.collectAsState()
        val items by viewModel.items.collectAsState()
        val collectionItemPairs = items.collectionItems.zipWithNext { a, b ->
            return@zipWithNext if (items.collectionItems.indexOf(a) % 2 == 0) {
                Pair(a, b)
            } else null
        }.filterNotNull()

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 56.dp, bottom = 56.dp)
        ) {
            item {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                        .height(56.dp),
                    value = searchKeyword,
                    placeholder = { Text(stringResource(R.string.search)) },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.search)
                        )
                    },
                    textStyle = MaterialTheme.typography.body1,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.primaryVariant,
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    onValueChange = { viewModel.searchKeyword.value = it }
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(R.string.favorite_collections).toUpperCase(Locale.ENGLISH),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                LazyRow {
                    items(collectionItemPairs) {
                        Column {
                            CollectionCard(title = it.first.title, imageRes = it.first.imageRes)
                            Spacer(modifier = Modifier.height(8.dp))
                            CollectionCard(title = it.second.title, imageRes = it.second.imageRes)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(36.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(R.string.align_your_body).toUpperCase(Locale.ENGLISH),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                LazyRow {
                    items(items.bodyItems) {
                        Column {
                            ExerciseCard(title = it.title, imageRes = it.imageRes)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(36.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(R.string.align_your_mind).toUpperCase(Locale.ENGLISH),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                LazyRow {
                    items(items.mindItems) {
                        Column {
                            ExerciseCard(title = it.title, imageRes = it.imageRes)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CollectionCard(title: String, @DrawableRes imageRes: Int) {
    Surface(
        modifier = Modifier
            .width(192.dp)
            .height(56.dp)
            .padding(end = 8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = title,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}

@Composable
fun ExerciseCard(title: String, @DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier.padding(end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            painter = painterResource(imageRes),
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.height(24.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.primaryVariant
            )
        }
    }
}
