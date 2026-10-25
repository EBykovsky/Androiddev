
package com.example.androiddev.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.androiddev.ui.screens.SettingsScreen

@Composable
fun SettingsDialog(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        SettingsScreen(
            selectedLanguage = selectedLanguage,
            onLanguageSelected = onLanguageSelected,
            onSave = onSave
        )
    }
}
