package com.example.test_app.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R

@Composable
fun SearchBar(
    filterCount: Int = 0,
    onFilter: () -> Unit = {},
    isSorted: Boolean = false,
    onSort: () -> Unit = {},
    textFieldValue: String? = null,
    onTextChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .padding(dimensionResource(R.dimen.grid_unit_8x))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_8x)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            SearchTextField(textFieldValue, onTextChange, onSearch)
        }
        FilterButton(filterCount, onFilter)
        SortButton(isSorted, onSort)
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}
