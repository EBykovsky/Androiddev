
package com.example.androiddev

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androiddev.domain.GameDifficulty
import com.example.androiddev.ui.screens.GameScreen
import com.example.androiddev.ui.theme.AndroidDevTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.androiddev.ui.components.RulesDialog

class GameActivity : ComponentActivity() {

    companion object {
        const val EXTRA_DIFFICULTY = "difficulty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val difficultyName =
            intent.getStringExtra(EXTRA_DIFFICULTY)

        val difficulty = GameDifficulty.entries.firstOrNull {
            it.name == difficultyName
        } ?: GameDifficulty.NORMAL

        setContent {
            AndroidDevTheme {

                var showRules by remember {
                    mutableStateOf(false)
                }

                GameScreen(
                    difficulty = difficulty,

                    onHomeClick = {
                        val intent = Intent(
                            this,
                            MainActivity::class.java
                        ).apply {
                            addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                            )
                        }

                        startActivity(intent)
                        finish()
                    },

                    onInfoClick = {
                        showRules = true
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
        }
    }
}
