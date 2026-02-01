package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Details(navHostController: NavHostController, sharedViewModel: SharedViewModel) {
    val puro = sharedViewModel.selectedPuro!!

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text("Nombre: ${puro.name}")
        Text("Id: ${puro.id}")
        Text("Precio: ${puro.price}")

        Image(
            painter = painterResource(id = puro.icon),
            contentDescription = puro.name,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Quantity selector
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Button(
                onClick = { if (sharedViewModel.cantidad > 1) sharedViewModel.cantidad-- }
            ) {
                Text("-")
            }

            Text(
                text = sharedViewModel.cantidad.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Button(
                onClick = { sharedViewModel.cantidad++ }
            ) {
                Text("+")
            }
        }

        // Buy button
        Button(
            onClick = {
                navHostController.navigate("cart")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buy (${sharedViewModel.cantidad})")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navHostController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go back")
        }
    }
}
