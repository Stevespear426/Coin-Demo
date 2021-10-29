package com.spear.coindemo.viewmodel.settings

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomPref(title: String, @DrawableRes icon: Int? = null, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .testTag("Test Row $title")
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .testTag("Test Icon")
            )
        }
        Text(
            title, style = MaterialTheme.typography.h6,
            modifier = Modifier.testTag("Test Title")
        )
    }
}
