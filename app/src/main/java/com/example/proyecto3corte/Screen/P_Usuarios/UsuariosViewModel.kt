package com.example.proyecto3corte.Screen.P_Usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Model.Usuario
import kotlinx.coroutines.launch

class UsuariosViewModel(private val usuariosRepository: UsuariosRepository) : ViewModel() {
    var usuarios = listOf<Usuario>()
        private set

    init {
        viewModelScope.launch {
            usuarios = usuariosRepository.getAllUsuarios()
        }
    }

    fun addUsuario(usuario: Usuario) = viewModelScope.launch {
        usuariosRepository.insert(usuario)
        usuarios = usuariosRepository.getAllUsuarios()
    }

    fun updateUsuario(usuario: Usuario) = viewModelScope.launch {
        usuariosRepository.update(usuario)
        usuarios = usuariosRepository.getAllUsuarios()
    }

    fun deleteUsuario(usuario: Usuario) = viewModelScope.launch {
        usuariosRepository.delete(usuario)
        usuarios = usuariosRepository.getAllUsuarios()
    }

    fun getUsuarioById(id: Int): Usuario? {
        return usuarios.find { it.id_usuario == id }
    }
}