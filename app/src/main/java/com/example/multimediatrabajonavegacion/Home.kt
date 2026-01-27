package com.example.multimediatrabajonavegacion

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun Home() {
    val tiposPuros = listOf(
        object {
            val name = "Camel"
            val id = "camel"
        },
        object {
            val name = "Marlboro"
            val id = "marlboro"
        },
        object {
            val name = "Newport"
            val id = "newport"
        },
        object {
            val name = "American Spirit"
            val id = "american-spirit"
        },
        object {
            val name = "Pall Mall"
            val id = "pall-mall"
        },
        object {
            val name = "Memphis"
            val id = "memphis"
        }
    )

    LazyColumn() {

    }
}