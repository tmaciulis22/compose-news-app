package com.example.test_app.ui.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.test_app.R

@Composable
fun NewsCard(
    imageUrl: String,
    title: String,
    description: String,
    newsUrl: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable{ onClick(newsUrl) }
            .height(dimensionResource(R.dimen.grid_unit_62x)),
        elevation = dimensionResource(R.dimen.grid_unit_8x)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = rememberImagePainter(imageUrl),
                modifier = Modifier.width(dimensionResource(R.dimen.grid_unit_70x)),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(id = R.string.content_description_news_item_image)
            )
            Column(Modifier.padding(dimensionResource(R.dimen.grid_unit_10x))) {
                Text(
                    title,
                    style = MaterialTheme.typography.h2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    description,
                    style = MaterialTheme.typography.body1,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    NewsCard(
        imageUrl = "https://www.siliconrepublic.com/wp-content/uploads/2020/03/BOO_3353_2.jpg",
        title = "Nick Leeder appointed as latest head of Google Ireland",
        description = "Google has announced that Nick Leeder will replace Fionnuala Meehan as the head of its Irish operation starting in April.",
        newsUrl = "https://www.siliconrepublic.com/companies/nick-leeder-google-ireland",
        onClick = {}
    )
}
