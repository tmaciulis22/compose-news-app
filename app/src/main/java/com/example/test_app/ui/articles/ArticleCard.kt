package com.example.test_app.ui.articles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.example.test_app.ui.view.LoadingImage
import com.example.test_app.ui.view.LoadingText

@Composable
fun ArticleCard(
    isLoading: Boolean = false,
    imageUrl: String? = null,
    title: String? = null,
    description: String? = null,
    articleUrl: String? = null,
    onClick: ((String) -> Unit) = {}
) {
    Card(
        modifier = Modifier.clickable{ onClick(articleUrl ?: "") },
        elevation = dimensionResource(R.dimen.grid_unit_8x)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            LoadingImage(
                isLoading = isLoading,
                imageUrl = imageUrl,
                modifier = Modifier.size(
                    width = dimensionResource(R.dimen.grid_unit_72x),
                    height = dimensionResource(R.dimen.grid_unit_65x)
                ),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.content_description_news_item_image)
            )
            Column(
                Modifier.padding(dimensionResource(R.dimen.grid_unit_10x)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_2x))
            ) {
                LoadingText(
                    isLoading = isLoading,
                    text = title,
                    style = MaterialTheme.typography.h2,
                    maxLines = 1
                )
                LoadingText(
                    isLoading = isLoading,
                    text = description,
                    style = MaterialTheme.typography.body1,
                    maxLines = 4
                )
            }
        }
    }
}

@Preview
@Composable
fun ArticleCardPreview() {
    ArticleCard(
        imageUrl = "https://www.siliconrepublic.com/wp-content/uploads/2020/03/BOO_3353_2.jpg",
        title = "Nick Leeder appointed as latest head of Google Ireland",
        description = "Google has announced that Nick Leeder will replace Fionnuala Meehan as the head of its Irish operation starting in April.",
        articleUrl = "https://www.siliconrepublic.com/companies/nick-leeder-google-ireland",
        onClick = {}
    )
}

@Preview
@Composable
fun ArticleCardLoadingPreview() {
    ArticleCard(isLoading = true)
}
