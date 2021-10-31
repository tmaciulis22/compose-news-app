package com.example.test_app.ui.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.test_app.R

@Composable
fun ArrowBackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Outlined.ArrowBack,
            stringResource(id = R.string.content_description_app_bar_navigation_icon),
            tint = MaterialTheme.colors.primary
        )
    }
}
