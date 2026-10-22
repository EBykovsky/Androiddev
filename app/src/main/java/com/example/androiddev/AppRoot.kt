git add -A
package com.example.androiddev

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.androiddev.ui.screens.MainScreen

@Composable
fun AppRoot(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val message = stringResource(R.string.feature_not_ready)

    val showPlaceholder = {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    MainScreen(
        modifier = modifier,
        onStartClick = showPlaceholder,
        onRulesClick = showPlaceholder,
        onSettingsClick = showPlaceholder
    )
}
