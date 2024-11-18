package com.example.proyecto3corte.Screen.P_Usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Model.Usuario
import kotlinx.coroutines.launch

class MasDetallesUsuariosViewModel(private val usuariosRepository: UsuariosRepository) : ViewModel() {
    var usuario: Usuario? = null
        private set

    fun loadUsuario(id: Int) {
        viewModelScope.launch {
            usuario = usuariosRepository.getUsuarioById(id)
        }
    }
}

