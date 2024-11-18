package com.example.proyecto3corte.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto3corte.Model.UsuarioMedicamento


@Dao
interface UsuariosMedicamentosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuarioMedicamento: UsuarioMedicamento)

    @Update
    suspend fun update(usuarioMedicamento: UsuarioMedicamento)

    @Delete
    suspend fun delete(usuarioMedicamento: UsuarioMedicamento)

    @Query("SELECT * FROM UsuarioMedicamento WHERE id_usuario = :id_usuario AND id_medicamento = :id_medicamento")
    suspend fun getUsuarioMedicamento(id_usuario: Int, id_medicamento: Int): UsuarioMedicamento?

    @Query("SELECT * FROM UsuarioMedicamento")
    suspend fun getAllUsuarioMedicamentos(): List<UsuarioMedicamento>
}
