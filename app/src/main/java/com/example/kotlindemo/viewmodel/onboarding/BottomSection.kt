package com.example.kotlindemo.viewmodel.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        //indicators
        Indicators(size = size, index = index)

        //next button
        FloatingActionButton(
            onClick = onNextClicked,
            modifier = Modifier.align(Alignment.CenterEnd).testTag("FAB"),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            if (index == size - 1)
                Icon(
                    Icons.Outlined.Done,
                    null,
                    Modifier.testTag("Done")
                )
            else
                Icon(
                    Icons.Outlined.KeyboardArrowRight,
                    null,
                    Modifier.testTag("KeyboardArrowRight")
                )
        }

    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(it, isSelected = it == index)
        }
    }
}


@Composable
fun Indicator(index: Int, isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            ).testTag("Indicator_${index}_${isSelected}")
    )
}

@Preview
@Composable
fun PreviewBottomSection() {
    BottomSection(size = 3, index = 1) {}
}