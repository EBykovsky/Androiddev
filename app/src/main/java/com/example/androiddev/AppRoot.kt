package com.example.androiddev

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.androiddev.ui.screens.MainScreen
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androiddev.ui.components.RulesDialog

@Composable
fun AppRoot(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val message = stringResource(R.string.feature_not_ready)

    var showRules by remember {
        mutableStateOf(false)
    }

    MainScreen(
        modifier = modifier,
        onStartClick = {
            val intent = Intent(
                context,
                DifficultyActivity::class.java
            )
            context.startActivity(intent)
        },
        onRulesClick = {
            showRules = true
        },
        onSettingsClick = {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    )
    if (showRules) {
        RulesDialog(
            onDismiss = {
                showRules = false
            }
        )
    }
}
