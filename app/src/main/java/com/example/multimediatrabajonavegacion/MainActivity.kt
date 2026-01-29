package com.example.multimediatrabajonavegacion


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Aplicamos el tema de Material 3 de tu proyecto
            MaterialTheme {
                // Surface es el contenedor de fondo que usa los colores del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // LLAMAMOS AL ARCHIVO DE NAVEGACIÓN
                    // Este es el "cerebro" que decide qué pantalla mostrar
                    AppNavigation()
                }
            }
        }
    }
}