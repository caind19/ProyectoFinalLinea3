package com.example.proyecto3corte.Repository

import com.example.proyecto3corte.DAO.RecordatoriosDao
import com.example.proyecto3corte.Model.Recordatorio

class RecordatoriosRepository(private val dao: RecordatoriosDao) {
    suspend fun getAllRecordatorios(): List<Recordatorio> = dao.getAllRecordatorios()
    suspend fun insert(recordatorio: Recordatorio) = dao.insert(recordatorio)
    suspend fun update(recordatorio: Recordatorio) = dao.update(recordatorio)
    suspend fun delete(recordatorio: Recordatorio) = dao.deleteById(recordatorio.id_recordatorio) // Cambiar a 'deleteById'
    suspend fun getRecordatorioById(id: Int): Recordatorio? = dao.getRecordatorioById(id)
}
