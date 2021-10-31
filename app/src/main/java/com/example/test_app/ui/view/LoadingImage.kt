package com.example.test_app.ui.view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.test_app.R
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

// Shows Shimmer effect when image is still loading
@Composable
fun LoadingImage(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    imageUrl: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String? = null
) {
    Image(
        painter = rememberImagePainter(imageUrl),
        modifier = modifier.placeholder(
            visible = isLoading,
            highlight = PlaceholderHighlight.shimmer(),
        ),
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}

@Preview
@Composable
fun LoadingImageLoadedPreview() {
    LoadingImage(
        imageUrl = "https://www.siliconrepublic.com/wp-content/uploads/2020/03/BOO_3353_2.jpg",
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.content_description_news_item_image)
    )
}

@Preview
@Composable
fun LoadingImageStillLoadingPreview() {
    LoadingImage(
        isLoading = true
    )
}
