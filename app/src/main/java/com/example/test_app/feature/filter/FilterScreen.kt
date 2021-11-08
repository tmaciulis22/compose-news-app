package com.example.test_app.feature.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.test_app.R
import com.example.test_app.feature.search.SearchViewModel
import com.example.test_app.ui.navigation.Route
import com.example.test_app.ui.view.*

@Composable
fun FilterScreen(
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
                            searchViewModel.clearFilters()
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
                text = stringResource(R.string.filters_title),
                style = MaterialTheme.typography.h1
            )
            Text(
                text = stringResource(R.string.filters_subtitle_date),
                style = MaterialTheme.typography.h3
            )
            DateSelector(
                label = stringResource(R.string.filters_from_label),
                value = filterState.from
            ) {
                searchViewModel.setFromFilter(it)
            }
            DateSelector(
                label = stringResource(R.string.filters_to_label),
                value = filterState.to
            ) {
                searchViewModel.setToFilter(it)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Route.SearchIn.name) },
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_5x))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.filters_subtitle_search_in),
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = "TODO",
                        color = MaterialTheme.colors.secondary,
                        style = MaterialTheme.typography.h3
                    )
                }
                Divider(color = Color.Gray)
            }
            Spacer(modifier = Modifier.weight(1.0f))
            StyledButton(
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.grid_unit_10x)),
                text = stringResource(R.string.filters_button_text),
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
