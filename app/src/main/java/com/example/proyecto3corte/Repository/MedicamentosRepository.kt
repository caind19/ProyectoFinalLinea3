package com.example.proyecto3corte.Repository

import com.example.proyecto3corte.DAO.MedicamentosDao
import com.example.proyecto3corte.Model.Medicamento

class MedicamentosRepository(private val dao: MedicamentosDao) {
    suspend fun getAllMedicamentos(): List<Medicamento> = dao.getAllMedicamentos()
    suspend fun insert(medicamento: Medicamento) = dao.insert(medicamento)
    suspend fun update(medicamento: Medicamento) = dao.update(medicamento)
    suspend fun delete(medicamento: Medicamento) = dao.deleteById(medicamento.id_medicamento) // Cambiar a 'deleteById'
    suspend fun getMedicamentoById(id: Int): Medicamento? = dao.getMedicamentoById(id)
}

