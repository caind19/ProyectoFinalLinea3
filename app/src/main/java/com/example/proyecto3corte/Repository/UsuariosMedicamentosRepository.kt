package com.example.proyecto3corte.Repository

import com.example.proyecto3corte.DAO.UsuariosMedicamentosDao
import com.example.proyecto3corte.Model.UsuarioMedicamento

class UsuariosMedicamentosRepository(private val dao: UsuariosMedicamentosDao) {
    suspend fun getAllUsuarioMedicamentos(): List<UsuarioMedicamento> = dao.getAllUsuarioMedicamentos()
    suspend fun insert(usuarioMedicamento: UsuarioMedicamento) = dao.insert(usuarioMedicamento)
    suspend fun update(usuarioMedicamento: UsuarioMedicamento) = dao.update(usuarioMedicamento)
    suspend fun delete(usuarioMedicamento: UsuarioMedicamento) = dao.delete(usuarioMedicamento)
    suspend fun getUsuarioMedicamento(id_usuario: Int, id_medicamento: Int): UsuarioMedicamento? = dao.getUsuarioMedicamento(id_usuario, id_medicamento)
}
