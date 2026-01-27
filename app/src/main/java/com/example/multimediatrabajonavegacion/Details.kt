package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Details(puro: Puro) {
    Column(
        modifier = Modifier.systemBarsPadding()
    ) {
        Text("Nombre: " + puro.name)
        Text("Id: " + puro.id)
        Text("Precio: " + puro.precio)
    }
}