package com.example.test_app.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R

@Composable
fun LabeledSwitcher(
    label: String,
    checked: Boolean,
    onSwitch: (String, Boolean) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_5x))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.h3
            )
            StyledSwitch(checked = checked) { isChecked ->
                onSwitch(label, isChecked)
            }
        }
        Divider(color = Color.LightGray)
    }
}

@Preview
@Composable
fun LabeledSwitcherPreview() {
    LabeledSwitcher(label = "Title", checked = true)
}
