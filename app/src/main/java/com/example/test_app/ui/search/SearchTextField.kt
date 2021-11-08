package com.example.test_app.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R

@Composable
fun SearchTextField(
    onSearch: (String) -> Unit = {}
) {
    var textState by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textState,
        onValueChange = { textState = it },
        textStyle = MaterialTheme.typography.h3,
        singleLine = true,
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
        placeholder = {
            Text(
                stringResource(R.string.search_bar_text_field_label),
                style = MaterialTheme.typography.h3
            )
        },
        shape = RoundedCornerShape(dimensionResource(R.dimen.grid_unit_50x)),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = MaterialTheme.colors.onSurface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(textState)
            textState = ""
            focusManager.clearFocus()
        })
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField()
}
