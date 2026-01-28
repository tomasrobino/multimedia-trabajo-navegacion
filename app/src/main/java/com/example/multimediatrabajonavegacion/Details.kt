package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Details(puro: Puro) {
    Column(
        modifier = Modifier.systemBarsPadding()
    ) {
        Text("Nombre: " + puro.name)
        Text("Id: " + puro.id)
        Text("Precio: " + puro.price)
        Image(
            painter = painterResource(id = puro.icon),
            contentDescription = puro.name
        )
    }
}