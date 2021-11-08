package com.example.test_app.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.test_app.R
import com.example.test_app.ui.view.LoadingText

@Composable
fun SearchHistoryItem(
    isLoading: Boolean = false,
    searchText: String? = null,
    onClick: (String) -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onClick(searchText ?: "") },
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_8x))
    ) {
        LoadingText(isLoading, searchText, MaterialTheme.typography.h3)
        Divider(color = Color.White)
    }
}
