package com.example.test_app.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test_app.R
import com.example.test_app.ui.theme.TestappTheme

@Composable
fun AppBar(
    isRounded: Boolean = false,
    isElevated: Boolean = true,
    isPadded: Boolean = true,
    title: @Composable () -> Unit = { AppLogo() },
    navigationButton: (@Composable () -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null,
    bottomContent: (@Composable () -> Unit)? = null
) {
    val showOnlyTitle = navigationButton == null && actions == null
    val cornerRadius = if (isRounded) dimensionResource(R.dimen.grid_unit_10x) else 0.dp
    val elevation = if (isElevated) dimensionResource(R.dimen.grid_unit_8x) else 0.dp

    Column (
        modifier = Modifier
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius
                ),
                clip = true
            )
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    if (isPadded) dimensionResource(R.dimen.grid_unit_8x) else 0.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (showOnlyTitle) Arrangement.Center else Arrangement.SpaceBetween
        ) {
            navigationButton?.invoke()
            title()
            actions?.invoke()
        }
        bottomContent?.invoke()
    }
}

@Preview
@Composable
fun AppBarPreview() {
    TestappTheme {
        AppBar(isRounded = true)
    }
}

@Preview
@Composable
fun AppBarWithButtonsPreview() {
    TestappTheme {
        AppBar(
            isRounded = false,
            navigationButton = { ArrowBackButton {} },
            actions = { ClearButton {} }
        )
    }
}
