package com.example.test_app.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.test_app.R

@Composable
fun FilterButton(
    filterCount: Int = 0,
    onClick: () -> Unit = {}
) {
    Box {
        if (filterCount > 0) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primaryVariant,
                        shape = CircleShape
                    )
                    .size(
                        width = dimensionResource(R.dimen.grid_unit_8x),
                        height = dimensionResource(R.dimen.grid_unit_8x)
                    )
                    .align(Alignment.TopEnd)
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = filterCount.toString(),
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
            }
        }
        IconButton(
            modifier = Modifier
                .background(
                    MaterialTheme.colors.background,
                    CircleShape
                )
                .size(dimensionResource(R.dimen.grid_unit_27x)),
            onClick = onClick
        ) {
            Icon(
                painterResource(R.drawable.ic_filter),
                stringResource(R.string.content_description_filter_icon),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
fun FilterButtonPreview() {
    FilterButton()
}

@Preview
@Composable
fun FilterButtonWithCountPreview() {
    FilterButton(3)
}
