package com.example.proyecto3corte.Screen.P_Usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Model.Usuario
import kotlinx.coroutines.launch
import java.time.LocalDate

class AgregarEditarUsuariosViewModel(private val usuariosRepository: UsuariosRepository) : ViewModel() {
    var usuario = Usuario(0, "", "", "", "", "", "", LocalDate.now())
        private set

    fun loadUsuario(id: Int) {
        viewModelScope.launch {
            usuario = usuariosRepository.getUsuarioById(id) ?: Usuario(0, "", "", "", "", "", "", LocalDate.now())
        }
    }

    fun onUsuarioIdChanged(id: Int) {
        usuario = usuario.copy(id_usuario = id)
    }

    fun onNombre1Changed(nombre: String) {
        usuario = usuario.copy(nombre1 = nombre)
    }

    fun onNombre2Changed(nombre: String) {
        usuario = usuario.copy(nombre2 = nombre)
    }

    fun onApellido1Changed(apellido: String) {
        usuario = usuario.copy(apellido1 = apellido)
    }

    fun onApellido2Changed(apellido: String) {
        usuario = usuario.copy(apellido2 = apellido)
    }

    fun onEmailChanged(email: String) {
        usuario = usuario.copy(email = email)
    }

    fun onTelefonoChanged(telefono: String) {
        usuario = usuario.copy(telefono = telefono)
    }

    fun onFechaNacimientoChanged(fecha: LocalDate) {
        usuario = usuario.copy(fecha_nacimiento = fecha)
    }

    fun saveUsuario() {
        viewModelScope.launch {
            if (usuario.id_usuario == 0) {
                usuariosRepository.insert(usuario)
            } else {
                usuariosRepository.update(usuario)
            }
        }
    }
}
