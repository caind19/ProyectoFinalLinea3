package com.example.proyecto3corte.Repository

import com.example.proyecto3corte.DAO.UsuariosDao
import com.example.proyecto3corte.Model.Usuario

class UsuariosRepository(private val dao: UsuariosDao) {
    suspend fun getAllUsuarios(): List<Usuario> = dao.getAllUsuarios()
    suspend fun insert(usuario: Usuario) = dao.insert(usuario)
    suspend fun update(usuario: Usuario) = dao.update(usuario)
    suspend fun delete(usuario: Usuario) = dao.deleteById(usuario.id_usuario) // Cambiar a 'deleteById'
    suspend fun getUsuarioById(id: Int): Usuario? = dao.getUsuarioById(id)
}

