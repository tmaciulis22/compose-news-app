package com.example.test_app.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.example.test_app.ui.theme.Background
import com.example.test_app.ui.theme.TestappTheme

@Composable
fun AppBar(
    isRounded: Boolean = true,
    onNavigationButtonClick: (() -> Unit)? = null,
    onClearButtonClick: (() -> Unit)? = null
) {
    val topBarModifier = if (isRounded) 
        Modifier.clip(
            RoundedCornerShape(
                bottomStart = dimensionResource(R.dimen.grid_unit_10x),
                bottomEnd = dimensionResource(R.dimen.grid_unit_10x)
            )
        )
    else
        Modifier

    TopAppBar(
        modifier = topBarModifier,
        title = {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.content_description_app_bar_logo)
            )
        },
        navigationIcon = onNavigationButtonClick?.let {
            {
                IconButton(onClick = it) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        stringResource(id = R.string.content_description_app_bar_navigation_icon)
                    )
                }
            }
        },
        actions = onClearButtonClick?.let {
            {
                Text(
                    text = stringResource(id = R.string.app_bar_clear_button_text)
                )
            }
        } ?: {},
        backgroundColor = MaterialTheme.colors.surface
    )
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
