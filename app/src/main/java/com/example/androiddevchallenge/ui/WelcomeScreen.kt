package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R

@Composable
fun Welcome(darkTheme: Boolean) {
    Image(
        painter = painterResource(
            id = if (darkTheme) R.drawable.ic_dark_welcome_illos else R.drawable.ic_light_welcome_illos
        ),
        contentDescription = stringResource(R.string.welcome_leaf_picture)
    )
}