package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(onNavigateToHome: () -> Unit) {
    // Datos de ejemplo (Esto luego vendrá de un ViewModel o parámetros)
    val purosEnCarrito = listOf(
        "Puro Habano" to 45.0,
        "Puro Amazonico" to 30.0
    )
    val total = purosEnCarrito.sumOf { it.second }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Resumen de Compra") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Lista de productos en el carrito
            Text(
                text = "Tus artículos:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(purosEnCarrito) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = item.first)
                            Text(text = "${item.second}€", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            // Resumen de precio
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Total a pagar:", fontSize = 20.sp)
                Text(
                    text = "${total}€",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para volver al Home (Requisito de la actividad)
            Button(
                // onNavigateToHome() dentro de onClick para volver a la pantalla de inicio
                onClick = { onNavigateToHome() },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Volver al Inicio")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartPreview() {
    MaterialTheme {
        CartScreen(onNavigateToHome = {})
    }
}