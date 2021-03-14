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
package com.example.androiddevchallenge.data

class HomeRepository : HomeContract.Repository {
    override fun getCollections() = listOf(
        "Short mantras", "Nature meditations", "Stress and anxiety", "Self-massage", "Overwhelmed", "Nightly wind down"
    )

    override fun getBodyExercises() = listOf(
        "Inversions", "Quick yoga", "Stretching", "Tabata", "HIIT", "Pre-natal yoga"
    )

    override fun getMindExercises() = listOf(
        "Meditate", "With kids", "Aromatherapy", "On the go", "With pets", "High stress"
    )
}
