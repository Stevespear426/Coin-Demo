package com.spear.coindemo.repository.use_case.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import javax.inject.Inject

class LaunchUrlUseCase @Inject constructor() {
    operator fun invoke(context: Context, url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        ContextCompat.startActivity(context, openURL, null)
    }
}