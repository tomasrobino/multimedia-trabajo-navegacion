package com.example.multimediatrabajonavegacion

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

data class Puro(
    val name: String,
    val id: String,
    val precio: Double
)

fun loadPurosFromAssets(context: Context): List<Puro> {
    val jsonString = context.assets.open("data.json")
        .bufferedReader()
        .use { it.readText() }

    val listType = object : TypeToken<List<Puro>>() {}.type
    return Gson().fromJson(jsonString, listType)
}

@Composable
fun Home(modifier: Modifier) {
    val context = LocalContext.current
    var filtro by remember { mutableStateOf<(Puro) -> Boolean>({true} ) }
    var tiposPuros by remember { mutableStateOf(listOf<Puro>()) }
    var purosFiltrados by remember { mutableStateOf(tiposPuros) }
    var selectedId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        tiposPuros = loadPurosFromAssets(context)
        purosFiltrados = tiposPuros
    }

    //var purosFiltrados = tiposPuros.filter { p -> filtro(p) }


    Column() {
        LazyColumn(
            modifier = Modifier.systemBarsPadding()
        ) {
            items(items = purosFiltrados, key = { it.id }) {
                    puro -> PuroRow(puro, selected = puro.id == selectedId, onClick = {selectedId = puro.id})
            }
        }
        Button(onClick = {
            purosFiltrados = purosFiltrados.sortedByDescending { puro -> puro.precio }
        }) {
            Text(text = "Ordenar menor a mayor")
        }
        Text(
            text = if (selectedId != null) {
                "El seleccionado es: ${tiposPuros.filter { puro -> puro.id == selectedId }[0].name}"
            } else {
                "Sin seleccion"
            }
        )
        Button(onClick = {}) {
            Text("Confirm")
        }
    }

}

@Composable
fun PuroRow(puro: Puro, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = puro.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}