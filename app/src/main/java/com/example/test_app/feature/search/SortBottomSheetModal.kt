package com.example.test_app.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.test_app.R
import com.example.test_app.api.entity.SortBy
import com.example.test_app.ui.view.LabeledCheckbox

@Composable
fun SortBottomSheetModal(
    searchViewModel: SearchViewModel
) {
    val filterState = searchViewModel.filterState

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.grid_unit_10x),
                horizontal = dimensionResource(R.dimen.grid_unit_8x)
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_10x))
    ) {
        Text(
            text = stringResource(R.string.bottom_sheet_sort_by_title),
            style = MaterialTheme.typography.h1
        )
        Divider(color = Color.LightGray)
        LabeledCheckbox(
            label = stringResource(R.string.sort_by_upload_date),
            isChecked = filterState.sortBy == SortBy.PublishedAt
        ) { wasAlreadyChecked ->
            if (!wasAlreadyChecked) {
                searchViewModel.setSortBy(SortBy.PublishedAt)
                if (!filterState.queryText.isNullOrBlank()) {
                    searchViewModel.getArticles()
                }
            }
        }
        Divider(color = Color.LightGray)
        LabeledCheckbox(label = stringResource(R.string.sort_by_relevance), isChecked = filterState.sortBy == SortBy.Relevance) { wasAlreadyChecked ->
            if (!wasAlreadyChecked) {
                searchViewModel.setSortBy(SortBy.Relevance)
                if (!filterState.queryText.isNullOrBlank()) {
                    searchViewModel.getArticles()
                }
            }
        }
    }
}
