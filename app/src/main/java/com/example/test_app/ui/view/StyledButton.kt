package com.example.test_app.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = ""
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.grid_unit_25x))
            .padding(horizontal = dimensionResource(R.dimen.grid_unit_8x)),
        onClick = onClick,
        shape = RoundedCornerShape(dimensionResource(R.dimen.grid_unit_278x)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}

@Preview
@Composable
fun StyledButtonPreview() {
    StyledButton()
}