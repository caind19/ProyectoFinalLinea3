package com.example.proyecto3corte.Screen.P_Medicamentos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Model.Medicamento
import kotlinx.coroutines.launch

class AgregarEditarMedicamentosViewModel(private val medicamentosRepository: MedicamentosRepository) : ViewModel() {
    var medicamento = Medicamento(0, "", "", "")
        private set

    fun loadMedicamento(id: Int) {
        viewModelScope.launch {
            medicamento = medicamentosRepository.getMedicamentoById(id) ?: Medicamento(0, "", "", "")
        }
    }

    fun onNombreChanged(nombre: String) {
        medicamento = medicamento.copy(nombre = nombre)
    }

    fun onDescripcionChanged(descripcion: String) {
        medicamento = medicamento.copy(descripcion = descripcion)
    }

    fun onFabricanteChanged(fabricante: String) {
        medicamento = medicamento.copy(fabricante = fabricante)
    }

    fun saveMedicamento() {
        viewModelScope.launch {
            if (medicamento.id_medicamento == 0) {
                medicamentosRepository.insert(medicamento)
            } else {
                medicamentosRepository.update(medicamento)
            }
        }
    }
}
