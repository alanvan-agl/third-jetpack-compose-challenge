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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.HomeContract
import com.example.androiddevchallenge.ui.home.data.HomeItems
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(private val repository: HomeContract.Repository) : ViewModel() {
    val items = MutableStateFlow(HomeItems(emptyList(), emptyList(), emptyList()))
    val searchKeyword = MutableStateFlow("")

    init {
        getItems()
    }

    private fun getItems() {
        items.value = HomeItems(
            collectionItems = repository.getCollections().map {
                HomeItems.CollectionItem(
                    title = it,
                    imageRes = when (it) {
                        "Short mantras" -> R.drawable.short_mantras
                        "Nature meditations" -> R.drawable.nature_meditations
                        "Stress and anxiety" -> R.drawable.stress_and_anxiety
                        "Self-massage" -> R.drawable.self_massage
                        "Nightly wind down" -> R.drawable.nightly_wind_down
                        "Overwhelmed" -> R.drawable.overwhelmed
                        else -> throw Throwable("Unexpected type")
                    }
                )
            },
            bodyItems = repository.getBodyExercises().map {
                HomeItems.BodyItem(
                    title = it,
                    imageRes = when (it) {
                        "Inversions" -> R.drawable.inversion
                        "Quick yoga" -> R.drawable.quick_yoga
                        "Stretching" -> R.drawable.stretching
                        "Tabata" -> R.drawable.tabata
                        "HIIT" -> R.drawable.hiit
                        "Pre-natal yoga" -> R.drawable.pre_natal_yoga
                        else -> throw Throwable("Unexpected type")
                    }
                )
            },
            mindItems = repository.getMindExercises().map {
                HomeItems.MindItem(
                    title = it,
                    imageRes = when (it) {
                        "Meditate" -> R.drawable.meditate
                        "With kids" -> R.drawable.with_kids
                        "Aromatherapy" -> R.drawable.aromatherapy
                        "On the go" -> R.drawable.on_the_go
                        "With pets" -> R.drawable.with_kids
                        "High stress" -> R.drawable.high_stress
                        else -> throw Throwable("Unexpected type")
                    }
                )
            }
        )
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repository: HomeContract.Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
