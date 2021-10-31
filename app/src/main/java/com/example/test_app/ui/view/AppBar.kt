package com.example.test_app.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.example.test_app.ui.theme.TestappTheme

@Composable
fun AppBar(
    isRounded: Boolean = true,
    onNavigationButtonClick: (() -> Unit)? = null,
    onClearButtonClick: (() -> Unit)? = null
) {
    val showOnlyTitle = onNavigationButtonClick == null && onClearButtonClick == null
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
            onNavigationButtonClick?.let {
                NavigationButton(it)
            }
            AppBarTitle()
            onClearButtonClick?.let {
                ClearButton(it)
            }
        }
    }
}

@Composable
fun NavigationButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            Icons.Outlined.ArrowBack,
            stringResource(id = R.string.content_description_app_bar_navigation_icon),
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun AppBarTitle() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.content_description_app_bar_logo)
    )
}

@Composable
fun ClearButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clip(CircleShape)
            .clickable(onClick = onClick, role = Role.Button),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_bar_clear_button_text),
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary
        )
        Icon(
            Icons.Outlined.Delete,
            stringResource(id = R.string.content_description_app_bar_clear_icon),
            tint = MaterialTheme.colors.primary
        )
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
            onNavigationButtonClick = {},
            onClearButtonClick = {}
        )
    }
}
