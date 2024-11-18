package com.example.proyecto3corte.Screen.P_Usuarios

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto3corte.Model.Usuario
import com.example.proyecto3corte.Repository.UsuariosRepository
import kotlinx.coroutines.launch

@Composable
fun MasDetallesUsuariosScreen(
    navController: NavController,
    usuarioId: Int,
    usuariosRepository: UsuariosRepository
) {
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(usuarioId) {
        scope.launch {
            usuario = usuariosRepository.getUsuarioById(usuarioId)
        }
    }

    usuario?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Detalles del Usuario", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text("ID: ${it.id_usuario}")
            Text("Nombre: ${it.nombre1} ${it.nombre2 ?: ""}")
            Text("Apellidos: ${it.apellido1} ${it.apellido2 ?: ""}")
            Text("Email: ${it.email}")
            Text("Teléfono: ${it.telefono}")
            Text("Fecha de Nacimiento: ${it.fecha_nacimiento}")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    } ?: run {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Cargando detalles del usuario...")
        }
    }
}

