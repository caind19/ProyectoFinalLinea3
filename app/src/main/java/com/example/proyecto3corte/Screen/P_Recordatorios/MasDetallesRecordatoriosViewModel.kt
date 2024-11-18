package com.example.proyecto3corte.Screen.P_Recordatorios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Model.Recordatorio
import kotlinx.coroutines.launch

class MasDetallesRecordatoriosViewModel(private val recordatoriosRepository: RecordatoriosRepository) : ViewModel() {
    var recordatorio: Recordatorio? = null
        private set

    fun loadRecordatorio(id: Int) {
        viewModelScope.launch {
            recordatorio = recordatoriosRepository.getRecordatorioById(id)
        }
    }
}