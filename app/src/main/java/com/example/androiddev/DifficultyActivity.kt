
package com.example.androiddev

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

                        val intent = Intent(
                            this,
                            GameActivity::class.java
                        )

                        intent.putExtra(
                            GameActivity.EXTRA_DIFFICULTY,
                            difficulty.name
                        )

                        startActivity(intent)
                    }
                )
            }
        }
    }
}
