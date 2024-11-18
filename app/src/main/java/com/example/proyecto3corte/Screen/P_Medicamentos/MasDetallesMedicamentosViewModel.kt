package com.example.proyecto3corte.Screen.P_Medicamentos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Model.Medicamento
import kotlinx.coroutines.launch

class MasDetallesMedicamentosViewModel(private val medicamentosRepository: MedicamentosRepository) : ViewModel() {
    var medicamento: Medicamento? = null
        private set

    fun loadMedicamento(id: Int) {
        viewModelScope.launch {
            medicamento = medicamentosRepository.getMedicamentoById(id)
        }
    }
}
