package com.example.multimediatrabajonavegacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Item(
    var number: Int,
    val puro: Puro
)

class SharedViewModel: ViewModel() {
    var selectedPuro by mutableStateOf<Puro?>(null)
    var cantidad by mutableIntStateOf(1)
    var carrito by mutableStateOf<MutableList<Item>>(mutableListOf())
}