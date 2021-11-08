package com.example.test_app.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R

@Composable
fun SearchBar(
    filterCount: Int = 0,
    onFilter: () -> Unit = {},
    onSort: () -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    val cornerRadius = dimensionResource(R.dimen.grid_unit_10x)
    val elevation = dimensionResource(R.dimen.grid_unit_8x)

    Row(
        modifier = Modifier
            .shadow(
                shape = RoundedCornerShape(
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius
                ),
                elevation = elevation,
                clip = true
            )
            .background(color = MaterialTheme.colors.surface)
            .padding(dimensionResource(R.dimen.grid_unit_8x))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_8x)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1f)) {
            SearchTextField(onSearch)
        }
        FilterButton(filterCount, onFilter)
        SortButton(onSort)
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}
