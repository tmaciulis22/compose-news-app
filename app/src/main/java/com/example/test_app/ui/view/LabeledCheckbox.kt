package com.example.test_app.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.test_app.R
import com.example.test_app.api.entity.SortBy

@Composable
fun LabeledCheckbox(
    label: String,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    val backgroundColor = if (isChecked)
        MaterialTheme.colors.primary
    else
        MaterialTheme.colors.background

    Row(
        Modifier
            .fillMaxWidth()
            .clickable(role = Role.Button) { onCheckedChange(isChecked) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.h3
        )
        Box(
            Modifier
                .background(backgroundColor, CircleShape)
                .size(dimensionResource(R.dimen.grid_unit_12x))
        ) {
            if (isChecked) {
                Box(
                    Modifier
                        .background(Color.White, CircleShape)
                        .align(Alignment.Center)
                        .size(dimensionResource(R.dimen.grid_unit_5x))
                        .zIndex(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun CheckboxPreview() {
    LabeledCheckbox("Relevance", true)
}
