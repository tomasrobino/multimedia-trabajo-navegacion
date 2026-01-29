package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Definición de las rutas (para evitar errores de escritura)
object Destinos {
    const val BIENVENIDA = "bienvenida"
    const val LEGAL = "legal"
    const val HOME = "home"
    const val DETALLES = "detalles/{puroId}" // Ruta con argumento
    const val CARRITO = "carrito"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinos.BIENVENIDA
    ) {
        composable(Destinos.BIENVENIDA) {
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(Destinos.HOME) {
                        popUpTo(Destinos.BIENVENIDA) { inclusive = true }
                    }
                },
                onNavigateToLegal = {
                    navController.navigate(Destinos.LEGAL)
                }
            )
        }

        composable(Destinos.LEGAL) {
            LegalInfoScreen(onBack = { navController.popBackStack() })
        }

        // PANTALLA TEMPORAL PARA QUE COMPILE
        composable(Destinos.HOME) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text("Pantalla de Inicio (En desarrollo)")
                    Button(onClick = { navController.navigate(Destinos.CARRITO) }) {
                        Text("Ir al Carrito")
                    }
                }
            }
        }

        composable(Destinos.CARRITO) {
            CartScreen(onNavigateToHome = {
                navController.navigate(Destinos.HOME) {
                    popUpTo(Destinos.HOME) { inclusive = true }
                }
            })
        }
    }
}