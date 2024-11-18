package com.example.proyecto3corte.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto3corte.Model.Usuario

@Dao
interface UsuariosDao {
    @Query("SELECT * FROM Usuarios WHERE id_usuario = :id")
    suspend fun getUsuarioById(id: Int): Usuario?

    @Query("SELECT * FROM Usuarios")
    suspend fun getAllUsuarios(): List<Usuario>

    @Insert
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Query("DELETE FROM Usuarios WHERE id_usuario = :id")
    suspend fun deleteById(id: Int)
}
