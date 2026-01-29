package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.* // Librerías de Material 3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onNavigateToHome: () -> Unit,      // Acción si es mayor de edad
    onNavigateToLegal: () -> Unit      // Acción si es menor de edad
)
{

    // Estado para capturar la edad
    var edadInput by remember { mutableStateOf("") }

    // Surface es el contenedor principal en Material 3
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título con estilo Material 3
            Text(
                text = "Estanco Virtual",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Por favor, introduce tu edad:",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto versión Material 3
            OutlinedTextField(
                value = edadInput,
                onValueChange = { newValue ->
                    // Validación: Solo números
                    if (newValue.all { it.isDigit() }) {
                        edadInput = newValue
                    }
                },
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón versión Material 3
            Button(
                onClick = {
                    // 1. Convertimos el texto a número (si está vacío o no es número, será 0)
                    val edad = edadInput.toIntOrNull() ?: 0

                    // 2. Ejecutamos la navegación según la edad
                    if (edad >= 18) {
                        onNavigateToHome()
                    } else {
                        onNavigateToLegal()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Entrar")
            }
        }
    }
}

// Función para previsualizar la pantalla en el panel lateral
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePreview() {
    MaterialTheme {
        WelcomeScreen(
            onNavigateToHome = { },
            onNavigateToLegal = { }
        )
    }
}