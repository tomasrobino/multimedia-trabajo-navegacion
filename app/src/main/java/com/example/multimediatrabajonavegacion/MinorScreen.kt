package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LegalInfoScreen(onBack: () -> Unit) {
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
            // Icono de advertencia
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Advertencia",
                modifier = Modifier.size(80.dp),
                tint = Color.Red
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Título de aviso
            Text(
                text = "ACCESO DENEGADO",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Red,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto legal basado en el enunciado
            Text(
                text = "Según la Ley 28/2005, queda prohibida la venta y suministro de productos de tabaco a menores de 18 años.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de vuelta atrás
            Button(
                onClick = { onBack() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Volver a Bienvenida")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LegalPreview() {
    MaterialTheme {
        LegalInfoScreen(onBack = {})
    }
}