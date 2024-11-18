package com.example.proyecto3corte.Screen.P_Usuarios

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Model.Usuario
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun AgregarEditarUsuariosScreen(
    navController: NavController,
    usuarioId: Int?,
    usuariosRepository: UsuariosRepository
) {
    var usuario by remember { mutableStateOf(Usuario(0, "", "", "", "", "", "", LocalDate.now())) }
    val scope = rememberCoroutineScope()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(usuarioId) {
        if (usuarioId != null && usuarioId != 0) {
            usuario = usuariosRepository.getUsuarioById(usuarioId) ?: usuario
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Agregar/Editar Usuario", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        TextField(
            value = usuario.nombre1,
            onValueChange = { usuario = usuario.copy(nombre1 = it) },
            label = { Text("Primer Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = usuario.nombre2 ?: "",
            onValueChange = { usuario = usuario.copy(nombre2 = it) },
            label = { Text("Segundo Nombre (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = usuario.apellido1,
            onValueChange = { usuario = usuario.copy(apellido1 = it) },
            label = { Text("Primer Apellido") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = usuario.apellido2 ?: "",
            onValueChange = { usuario = usuario.copy(apellido2 = it) },
            label = { Text("Segundo Apellido (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = usuario.email,
            onValueChange = { usuario = usuario.copy(email = it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = usuario.telefono,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    usuario = usuario.copy(telefono = it)
                    errorMessage = null
                } else {
                    errorMessage = "El teléfono solo debe contener números."
                }
            },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = usuario.fecha_nacimiento.format(dateFormatter),
            onValueChange = {
                try {
                    usuario = usuario.copy(fecha_nacimiento = LocalDate.parse(it, dateFormatter))
                    errorMessage = null
                } catch (e: DateTimeParseException) {
                    errorMessage = "Fecha inválida. Use el formato: yyyy-MM-dd."
                }
            },
            label = { Text("Fecha de Nacimiento (yyyy-MM-dd)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                if (usuario.nombre1.isBlank() || usuario.apellido1.isBlank() || usuario.email.isBlank()) {
                    errorMessage = "Los campos obligatorios no pueden estar vacíos."
                    return@launch
                }

                try {
                    if (usuario.id_usuario == 0) {
                        usuariosRepository.insert(usuario)
                    } else {
                        usuariosRepository.update(usuario)
                    }
                    navController.popBackStack()
                } catch (e: Exception) {
                    errorMessage = "Error al guardar el usuario: ${e.localizedMessage}"
                }
            }
        }) {
            Text("Guardar")
        }
    }
}
