package com.example.test_app.ui.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StyledSwitch(checked: Boolean, onSwitch: (Boolean) -> Unit = {}) {
    Switch(
        checked = checked,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            uncheckedThumbColor = Color.White,
            checkedTrackColor = MaterialTheme.colors.primary,
            uncheckedTrackColor = MaterialTheme.colors.secondary
        ),
        onCheckedChange = onSwitch
    )
}

@Preview
@Composable
fun StyledSwitchCheckedPreview() {
    StyledSwitch(true)
}

@Preview
@Composable
fun StyledSwitchUncheckedPreview() {
    StyledSwitch(false)
}
