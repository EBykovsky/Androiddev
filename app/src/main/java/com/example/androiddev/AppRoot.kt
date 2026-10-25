package com.example.androiddev

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.androiddev.ui.screens.MainScreen
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androiddev.ui.components.RulesDialog
import com.example.androiddev.data.repository.LanguageRepository
import com.example.androiddev.ui.components.SettingsDialog


@Composable
fun AppRoot(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val languageRepository = remember(context) {
        LanguageRepository(context.applicationContext)
    }

    var showRules by remember {
        mutableStateOf(false)
    }

    var showSettings by remember {
        mutableStateOf(false)
    }

    var selectedLanguage by remember {
        mutableStateOf(languageRepository.getLanguage())
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
            selectedLanguage =
                languageRepository.getLanguage()

            showSettings = true
        }
    )

    if (showRules) {
        RulesDialog(
            onDismiss = {
                showRules = false
            }
        )
    }

    if (showSettings) {
        SettingsDialog(
            selectedLanguage = selectedLanguage,

            onLanguageSelected = { language ->
                selectedLanguage = language
            },

            onSave = {
                showSettings = false

                if (languageRepository.getLanguage() != selectedLanguage) {
                    languageRepository.saveLanguage(selectedLanguage)
                }
            },

            onDismiss = {
                showSettings = false
            }
        )
    }
}
