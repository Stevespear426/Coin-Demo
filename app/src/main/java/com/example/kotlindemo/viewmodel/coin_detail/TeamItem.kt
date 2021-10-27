package com.example.kotlindemo.viewmodel.coin_detail

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.kotlindemo.repository.model.Team

@Preview
@Composable
fun TeamItem(@PreviewParameter(SampleTeam::class) member: Team) {
    Text(
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier.testTag("Test TeamItem ${member.id}"),
        text = "${member.name} (${member.position})",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

class SampleTeam : PreviewParameterProvider<Team> {
    override val values = sequenceOf(
        Team(id = "", name = "John Doe", position = "Dev")
    )
    override val count: Int = 1
}