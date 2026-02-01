package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Details(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val puro = sharedViewModel.selectedPuro!!

    // Initializes cantidad to 1 each time Details is launched, to avoid problems
    LaunchedEffect(puro.id) {
        sharedViewModel.cantidad = 1
    }

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(16.dp)
    ) {

        // Product card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = shapes.large,
            elevation = cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = puro.name,
                    style = typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = puro.icon),
                    contentDescription = puro.name,
                    modifier = Modifier
                        .height(180.dp)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Price: ${puro.price} €",
                    style = typography.titleMedium
                )

                Text(
                    text = "Product ID: ${puro.id}",
                    style = typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quantity selector
        Text(
            text = "Quantity",
            style = typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (sharedViewModel.cantidad > 1) sharedViewModel.cantidad--
                }
            ) {
                Text("−")
            }

            Text(
                text = sharedViewModel.cantidad.toString(),
                style = typography.headlineSmall
            )

            Button(
                onClick = { sharedViewModel.cantidad++ }
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Buy button
        Button(
            onClick = {
                val first: Item? = sharedViewModel.carrito.firstOrNull { it.puro.id == puro.id }
                if (first == null) {
                    sharedViewModel.carrito.add(Item(sharedViewModel.cantidad, puro))
                } else {
                    first.number = sharedViewModel.cantidad;
                }
                navHostController.navigate("cart")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "Buy (${sharedViewModel.cantidad})",
                style = typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Back button (secondary)
        OutlinedButton(
            onClick = { navHostController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Go back")
        }
    }
}
