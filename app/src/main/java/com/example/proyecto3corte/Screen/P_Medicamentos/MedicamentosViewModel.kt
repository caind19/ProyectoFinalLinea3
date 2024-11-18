package com.example.proyecto3corte.Screen.P_Medicamentos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Model.Medicamento
import kotlinx.coroutines.launch

class MedicamentosViewModel(private val medicamentosRepository: MedicamentosRepository) : ViewModel() {
    var medicamentos = listOf<Medicamento>()
        private set

    init {
        viewModelScope.launch {
            medicamentos = medicamentosRepository.getAllMedicamentos()
        }
    }

    fun addMedicamento(medicamento: Medicamento) = viewModelScope.launch {
        medicamentosRepository.insert(medicamento)
        medicamentos = medicamentosRepository.getAllMedicamentos()
    }

    fun updateMedicamento(medicamento: Medicamento) = viewModelScope.launch {
        medicamentosRepository.update(medicamento)
        medicamentos = medicamentosRepository.getAllMedicamentos()
    }

    fun deleteMedicamento(medicamento: Medicamento) = viewModelScope.launch {
        medicamentosRepository.delete(medicamento)
        medicamentos = medicamentosRepository.getAllMedicamentos()
    }

    fun getMedicamentoById(id: Int): Medicamento? {
        return medicamentos.find { it.id_medicamento == id }
    }
}
