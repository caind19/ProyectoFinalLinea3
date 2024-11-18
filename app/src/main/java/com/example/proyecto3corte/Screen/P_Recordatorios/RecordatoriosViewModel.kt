package com.example.proyecto3corte.Screen.P_Recordatorios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Model.Recordatorio
import kotlinx.coroutines.launch

class RecordatoriosViewModel(private val recordatoriosRepository: RecordatoriosRepository) : ViewModel() {
    var recordatorios = listOf<Recordatorio>()
        private set

    init {
        viewModelScope.launch {
            recordatorios = recordatoriosRepository.getAllRecordatorios()
        }
    }

    fun addRecordatorio(recordatorio: Recordatorio) = viewModelScope.launch {
        recordatoriosRepository.insert(recordatorio)
        recordatorios = recordatoriosRepository.getAllRecordatorios()
    }

    fun updateRecordatorio(recordatorio: Recordatorio) = viewModelScope.launch {
        recordatoriosRepository.update(recordatorio)
        recordatorios = recordatoriosRepository.getAllRecordatorios()
    }

    fun deleteRecordatorio(recordatorio: Recordatorio) = viewModelScope.launch {
        recordatoriosRepository.delete(recordatorio)
        recordatorios = recordatoriosRepository.getAllRecordatorios()
    }

    fun getRecordatorioById(id: Int): Recordatorio? {
        return recordatorios.find { it.id_recordatorio == id }
    }
}
