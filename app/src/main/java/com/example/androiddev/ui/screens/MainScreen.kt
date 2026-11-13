
package com.example.androiddev.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddev.R
import com.example.androiddev.ui.components.GameMenuButton
import com.example.androiddev.ui.components.GameMenuLayout

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onRulesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    GameMenuLayout(modifier = modifier) { scale ->

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (376f * scale).dp),
            verticalArrangement = Arrangement.spacedBy(
                (14f * scale).dp
            )
        ) {
            GameMenuButton(
                text = stringResource(R.string.menu_start),
                scale = scale,
                onClick = onStartClick
            )

            GameMenuButton(
                text = stringResource(R.string.menu_rules),
                scale = scale,
                onClick = onRulesClick
            )

            GameMenuButton(
                text = stringResource(R.string.menu_settings),
                scale = scale,
                onClick = onSettingsClick
            )
        }
    }
}
