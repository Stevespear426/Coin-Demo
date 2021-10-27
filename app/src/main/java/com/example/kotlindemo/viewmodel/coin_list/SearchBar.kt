package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(text: String, onValueChange: (id: String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
            .testTag("Test SearchBar"),
        singleLine = true,
        label = { Text("Search") },
        placeholder = { Text("Coin") },
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colors.onSurface),
        value = text,
        onValueChange = onValueChange
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(text = "Bitcoin", onValueChange = {})
}