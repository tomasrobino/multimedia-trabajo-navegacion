package com.example.multimediatrabajonavegacion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    var selectedPuro by mutableStateOf<Puro?>(null)
}