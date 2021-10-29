package com.spear.coindemo.viewmodel.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spear.coindemo.R
import com.spear.coindemo.ui.theme.LightBlue

@Preview
@Composable
fun OnboardingItem(
    @PreviewParameter(SampleItem::class) item: OnboardingItemData
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .testTag("Onboarding Item")
    ) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(LightBlue),
            modifier = Modifier.testTag("Icon")
        )
        Text(
            text = stringResource(item.title),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.testTag("Title")
        )

        Text(
            text = stringResource(item.body),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .testTag("Body")
        )
    }
}

class OnboardingItemData(
    @StringRes
    val title: Int,
    @StringRes
    val body: Int,
    @DrawableRes
    val icon: Int,
) {
    companion object {
        val items: List<OnboardingItemData> = listOf(
            OnboardingItemData(
                R.string.onboarding_title_1,
                R.string.onboarding_body_1,
                R.drawable.engineering
            ),
            OnboardingItemData(
                R.string.onboarding_title_2,
                R.string.onboarding_body_2,
                R.drawable.coin
            ),
            OnboardingItemData(
                R.string.onboarding_title_3,
                R.string.onboarding_body_3,
                R.drawable.enjoy
            ),
        )
    }
}

class SampleItem : PreviewParameterProvider<OnboardingItemData> {
    override val values = sequenceOf(
        OnboardingItemData(
            R.string.onboarding_title_1,
            R.string.onboarding_body_1,
            R.drawable.engineering
        )
    )
    override val count: Int = 1
}