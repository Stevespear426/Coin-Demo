package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spear.coindemo.repository.model.ChangeDuration

@Composable
fun DurationRow(
    duration: ChangeDuration,
    changeDuration: (changeDuration: ChangeDuration) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp).testTag("Test DurationRow"),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DurationItem(
            Modifier.weight(1.0f), "1H", duration == ChangeDuration.HOURS_1
        ) {
            changeDuration(ChangeDuration.HOURS_1)
        }
        DurationItem(Modifier.weight(1.0f), "1D", duration == ChangeDuration.DAY) {
            changeDuration(ChangeDuration.DAY)
        }
        DurationItem(Modifier.weight(1.0f), "1W", duration == ChangeDuration.WEEK) {
            changeDuration(ChangeDuration.WEEK)
        }
        DurationItem(Modifier.weight(1.0f), "1M", duration == ChangeDuration.MONTH) {
            changeDuration(ChangeDuration.MONTH)
        }
        DurationItem(Modifier.weight(1.0f), "1Y", duration == ChangeDuration.YEAR) {
            changeDuration(ChangeDuration.YEAR)
        }
    }
}

@Preview
@Composable
fun DurationRowPreview() {
    DurationRow(ChangeDuration.HOURS_1, {})
}