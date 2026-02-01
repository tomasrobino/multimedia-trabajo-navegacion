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
        androidx.compose.material3.Card(
            modifier = Modifier.fillMaxWidth(),
            shape = androidx.compose.material3.MaterialTheme.shapes.large,
            elevation = androidx.compose.material3.CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = puro.name,
                    style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
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
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Product ID: ${puro.id}",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quantity selector
        Text(
            text = "Quantity",
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
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
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
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
            onClick = { navHostController.navigate("cart") },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "Buy (${sharedViewModel.cantidad})",
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Back button (secondary)
        androidx.compose.material3.OutlinedButton(
            onClick = { navHostController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Go back")
        }
    }
}
