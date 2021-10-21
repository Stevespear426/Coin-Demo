package com.example.kotlindemo.viewmodel.coin_list

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun SearchBar(text: String, onValueChange: (id: String) -> Unit) {
    OutlinedTextField(value = text, onValueChange = onValueChange)
}