package com.example.test_app.ui.view

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

// Shows Shimmer effect when loading process is happening
@Composable
fun LoadingText(
    isLoading: Boolean = false,
    text: String? = null,
    style: TextStyle = LocalTextStyle.current,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        modifier = Modifier
            .placeholder(
                visible = isLoading,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        text = text ?: stringResource(id = R.string.placeholder_text),
        style = style,
        maxLines = maxLines,
        overflow = overflow
    )
}

@Preview
@Composable
fun LoadingTextLoadedPreview() {
    LoadingText(
        text = "Nick Leeder appointed as latest head of Google Ireland"
    )
}

@Preview
@Composable
fun LoadingTextStillLoadingPreview() {
    LoadingText(
        isLoading = true
    )
}
