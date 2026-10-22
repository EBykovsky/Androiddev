
package com.example.androiddev

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androiddev.domain.GameDifficulty
import com.example.androiddev.ui.screens.DifficultyScreen
import com.example.androiddev.ui.theme.AndroidDevTheme

class DifficultyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AndroidDevTheme {
                DifficultyScreen(
                    onDifficultySelected = { difficulty ->

                        val message = when (difficulty) {
                            GameDifficulty.NORMAL ->
                                R.string.difficulty_normal_message

                            GameDifficulty.HARD ->
                                R.string.difficulty_hard_message
                        }

                        Toast.makeText(
                            this,
                            getString(message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
