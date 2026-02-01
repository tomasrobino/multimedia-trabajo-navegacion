package com.example.multimediatrabajonavegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComponent() {
    val sharedViewModel: SharedViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("home") { Home(navController, sharedViewModel) }
        composable("details") { Details(navController, sharedViewModel) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("legal") { LegalInfoScreen {
            navController.popBackStack()
        }
        }
    }
}