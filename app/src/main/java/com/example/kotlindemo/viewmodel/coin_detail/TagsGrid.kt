package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlindemo.repository.model.Tag
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TagsGrid(tags: List<Tag>) {
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        tags.forEach { tag ->
            Surface(
                Modifier
                    .wrapContentWidth(),
                shape = RoundedCornerShape(20),
                color = Color.Green
            ) {
                Text(
                    modifier = Modifier.padding(all = 10.dp),
                    text = tag.name,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}