package com.example.multimediatrabajonavegacion

import androidx.compose.runtime.Composable
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
        // 1. Pantalla de Bienvenida
        composable(Destinos.BIENVENIDA) {
            // Aquí llamas a tu WelcomeScreen
            // Le pasamos la lógica de navegación como lambda
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(Destinos.HOME) {
                        // Al ir al Home, quitamos la Bienvenida del historial
                        popUpTo(Destinos.BIENVENIDA) { inclusive = true }
                    }
                },
                onNavigateToLegal = {
                    navController.navigate(Destinos.LEGAL)
                }
            )
        }

        // 2. Pantalla Legal (Menores)
        composable(Destinos.LEGAL) {
            LegalInfoScreen(onBack = {
                navController.popBackStack()
            })
        }
/*
        // 3. Pantalla de Inicio (Catálogo)
        composable(Destinos.HOME) {
            HomeScreenContent(
                onNavigateToDetails = { id ->
                    navController.navigate("detalles/$id")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Pantalla de Detalles (Con paso de parámetros)
        composable(
            route = Destinos.DETALLES,
            arguments = listOf(navArgument("puroId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("puroId") ?: 0
            DetailsScreen(
                puroId = id,
                onNavigateToCart = { navController.navigate(Destinos.CARRITO) },
                onBack = { navController.popBackStack() }
            )
        }
*/
        // 5. Pantalla del Carrito
        composable(Destinos.CARRITO) {
            CartScreen(onNavigateToHome = {
                // Volver al Home limpiando la pila según el enunciado
                navController.navigate(Destinos.HOME) {
                    popUpTo(Destinos.HOME) { inclusive = true }
                }
            })
        }
    }
}