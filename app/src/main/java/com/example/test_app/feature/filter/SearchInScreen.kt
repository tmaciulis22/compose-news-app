package com.example.test_app.feature.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.test_app.R
import com.example.test_app.api.entity.SearchInAttribute
import com.example.test_app.feature.search.SearchViewModel
import com.example.test_app.ui.view.*

@Composable
fun SearchInScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {
    val filterState = searchViewModel.filterState

    Scaffold(
        topBar = {
            Column {
                AppBar(
                    isElevated = false,
                    isPadded = false,
                    navigationButton = {
                        ArrowBackButton {
                            navController.popBackStack()
                        }
                    },
                    actions = {
                        ClearButton {
                            searchViewModel.clearSearchInFilter()
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(horizontal = dimensionResource(R.dimen.grid_unit_8x))
                .padding(top = dimensionResource(R.dimen.grid_unit_20x))
                .padding(paddingValues)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_10x))
        ) {
            Text(
                text = stringResource(R.string.filters_subtitle_search_in),
                style = MaterialTheme.typography.h1
            )
            SearchInAttribute.allAttributes.forEach { attribute ->
                LabeledSwitcher(
                    label = stringResource(attribute.textRes),
                    checked = filterState.searchIn.contains(attribute)
                ) { label, isChecked ->
                    searchViewModel.setSearchInFilter(
                        SearchInAttribute.getAttributeByName(label),
                        isChecked
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1.0f))
            StyledButton(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.grid_unit_10x)),
                text = stringResource(R.string.search_in_button_text),
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
