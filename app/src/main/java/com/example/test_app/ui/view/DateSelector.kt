package com.example.test_app.ui.view

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.test_app.R
import com.example.test_app.extension.getActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateSelector(
    label: String = "",
    value: String? = null,
    onSelected: (String) -> Unit = {}
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                context
                    .getActivity()
                    ?.let { activity ->
                        showDatePicker(activity) {
                            onSelected(it)
                        }
                    }
            },
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.grid_unit_5x)),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value ?: stringResource(R.string.filters_date_placeholder),
                style = MaterialTheme.typography.h3,
                color = if (value.isNullOrEmpty())
                    MaterialTheme.colors.secondary
                else
                    MaterialTheme.colors.onSurface
            )
            Icon(
                painterResource(R.drawable.ic_calendar),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
        Divider(color = MaterialTheme.colors.primary)
    }
}

@Preview
@Composable
fun DateSelectorPreview() {
    DateSelector()
}

private fun showDatePicker(
    activity: AppCompatActivity,
    onSelected: (String) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    val outputDateFormat = SimpleDateFormat(
        activity.getString(R.string.date_picker_date_pattern),
        Locale.getDefault()
    ).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        onSelected(outputDateFormat.format(it))
    }
}
