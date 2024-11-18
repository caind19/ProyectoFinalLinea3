package com.example.proyecto3corte.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto3corte.Model.Medicamento

@Dao
interface MedicamentosDao {
    @Query("SELECT * FROM Medicamentos WHERE id_medicamento = :id")
    suspend fun getMedicamentoById(id: Int): Medicamento?

    @Query("SELECT * FROM Medicamentos")
    suspend fun getAllMedicamentos(): List<Medicamento>

    @Insert
    suspend fun insert(medicamento: Medicamento)

    @Update
    suspend fun update(medicamento: Medicamento)

    @Query("DELETE FROM Medicamentos WHERE id_medicamento = :id")
    suspend fun deleteById(id: Int)
}
