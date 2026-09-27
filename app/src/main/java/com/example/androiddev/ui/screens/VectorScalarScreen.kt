package com.example.androiddev.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddev.domain.VectorScalar
import com.example.androiddev.ui.theme.AndroidDevTheme

@Composable
fun VectorScalarScreen(modifier: Modifier = Modifier) {
    val firstVector = remember {
        VectorScalar.generateVector(5)
    }
    val secondVector = remember {
        VectorScalar.generateVector(5)
    }
    var output by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = firstVector.joinToString(", "),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Первый вектор") }
        )

        OutlinedTextField(
            value = secondVector.joinToString(", "),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Второй вектор") }
        )

        OutlinedTextField(
            value = output,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Результат") }
        )

        Button(
            onClick = {
                output = VectorScalar
                    .scalarProduct(firstVector, secondVector)
                    .toString()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вычислить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VectorScalarScreenPreview() {
    AndroidDevTheme {
        VectorScalarScreen()
    }
}