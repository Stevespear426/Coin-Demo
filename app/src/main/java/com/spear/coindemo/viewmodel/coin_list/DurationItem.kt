package com.spear.coindemo.viewmodel.coin_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spear.coindemo.ui.theme.LightBlue

@Composable
fun DurationItem(modifier: Modifier, text: String, selected: Boolean, onClick: () -> Unit) {
    val color = if (selected) LightBlue else Color.LightGray
    Column(
        modifier.testTag("Test DurationItem"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick,
            modifier = Modifier.testTag("Test DurationItem Button $text")
        ) {
            Text(
                text,
                color = color,
                modifier = Modifier.testTag("Test DurationItem Text")
            )
        }
        Divider(color = color, modifier = Modifier.height(2.dp))
    }
}

@Preview
@Composable
fun DurationItemPreview() {
    DurationItem(Modifier.wrapContentWidth(), "1H", false, {})
}