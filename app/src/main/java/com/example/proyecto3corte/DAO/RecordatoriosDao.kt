package com.example.proyecto3corte.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto3corte.Model.Recordatorio

@Dao
interface RecordatoriosDao {
    @Query("SELECT * FROM Recordatorios WHERE id_recordatorio = :id")
    suspend fun getRecordatorioById(id: Int): Recordatorio?

    @Query("SELECT * FROM Recordatorios")
    suspend fun getAllRecordatorios(): List<Recordatorio>

    @Insert
    suspend fun insert(recordatorio: Recordatorio)

    @Update
    suspend fun update(recordatorio: Recordatorio)

    @Query("DELETE FROM Recordatorios WHERE id_recordatorio = :id")
    suspend fun deleteById(id: Int)
}
