package com.example.test_app.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.example.test_app.ui.theme.TestappTheme

@Composable
fun AppBar(
    isRounded: Boolean = true,
    title: @Composable () -> Unit = { AppLogo() },
    navigationButton: (@Composable () -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null,
) {
    val showOnlyTitle = navigationButton == null && actions == null
    val cornerRadius = dimensionResource(R.dimen.grid_unit_10x)
    val elevation = dimensionResource(R.dimen.grid_unit_8x)

    val topBarModifier = if (isRounded)
        Modifier
            .graphicsLayer {
                shape = RoundedCornerShape(
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius
                )
                shadowElevation = elevation.toPx()
                clip = true
            }
    else
        Modifier

    TopAppBar(
        modifier = topBarModifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = dimensionResource(R.dimen.grid_unit_0x)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (showOnlyTitle) Arrangement.Center else Arrangement.SpaceBetween
        ) {
            navigationButton?.invoke()
            title()
            actions?.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    TestappTheme {
        AppBar()
    }
}

@Preview(showBackground = true)
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
