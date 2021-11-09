package com.example.test_app.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R

@Composable
fun SortButton(
    isSorted: Boolean = false,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = Modifier
            .background(
                if (isSorted) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                CircleShape
            )
            .size(dimensionResource(R.dimen.grid_unit_27x)),
        onClick = onClick
    ) {
        Icon(
            painterResource(R.drawable.ic_sort),
            stringResource(R.string.content_description_sort_icon),
            tint = if (isSorted) Color.White else MaterialTheme.colors.onSurface
        )
    }
}

@Preview
@Composable
fun SortButtonPreview() {
    SortButton()
}