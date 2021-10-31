package com.example.test_app.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.example.test_app.R

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
