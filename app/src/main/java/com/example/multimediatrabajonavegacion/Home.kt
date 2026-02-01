package com.example.multimediatrabajonavegacion

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

/* -------------------- DATA -------------------- */

data class Puro(
    val id: String,
    val name: String,
    val price: Double,
    val icon: Int
)

data class RawPuro(
    val id: String,
    val name: String,
    val price: Double
)

/* -------------------- HELPERS -------------------- */

fun iconForPuro(id: String): Int = when (id) {
    "camel" -> R.drawable.camel
    "marlboro" -> R.drawable.marlboro
    "newport" -> R.drawable.newport
    "american-spirit" -> R.drawable.american_spirit
    "pall-mall" -> R.drawable.pall_mall
    "memphis" -> R.drawable.memphis
    else -> R.drawable.ic_launcher_foreground
}

fun loadPurosFromAssets(context: Context): List<Puro> {
    val json = context.assets.open("data.json")
        .bufferedReader()
        .use { it.readText() }

    val type = object : TypeToken<List<RawPuro>>() {}.type
    val raw = Gson().fromJson<List<RawPuro>>(json, type)

    return raw.map {
        Puro(
            id = it.id,
            name = it.name,
            price = it.price,
            icon = iconForPuro(it.id)
        )
    }
}

/* -------------------- SCREEN -------------------- */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current

    var allPuros by remember { mutableStateOf(emptyList<Puro>()) }
    var selectedId by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf<Double?>(null) }

    val filteredPuros by remember(allPuros, searchQuery, maxPrice) {
        derivedStateOf {
            allPuros.filter { puro ->
                puro.name.contains(searchQuery, ignoreCase = true) &&
                        (maxPrice?.let { puro.price <= it } ?: true)
            }
        }
    }

    LaunchedEffect(Unit) {
        allPuros = loadPurosFromAssets(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona tu puro") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            /* -------- Filters -------- */

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Buscar por nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = maxPrice?.toString() ?: "",
                    onValueChange = { maxPrice = it.toDoubleOrNull() },
                    label = { Text("Precio máximo") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            /* -------- List -------- */

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(filteredPuros, key = { it.id }) { puro ->
                    PuroRow(
                        puro = puro,
                        selected = puro.id == selectedId,
                        onClick = {
                            selectedId = puro.id
                            sharedViewModel.selectedPuro = puro
                        }
                    )
                }
            }

            /* -------- Footer -------- */

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = selectedId?.let {
                        "Seleccionado: ${allPuros.first { p -> p.id == it }.name}"
                    } ?: "Sin selección",
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = { navHostController.navigate("details") },
                    enabled = sharedViewModel.selectedPuro != null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar")
                }

                OutlinedButton(
                    onClick = { navHostController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver a Bienvenida")
                }
            }
        }
    }
}

/* -------------------- ROW -------------------- */

@Composable
fun PuroRow(
    puro: Puro,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(puro.icon),
                contentDescription = puro.name,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = puro.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "€${puro.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
