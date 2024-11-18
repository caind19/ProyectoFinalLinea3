package com.example.proyecto3corte.Screen.P_Usuarios

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Model.Usuario
import kotlinx.coroutines.launch

@Composable
fun UsuariosScreen(navController: NavController, usuariosRepository: UsuariosRepository) {
    var usuarios by remember { mutableStateOf(listOf<Usuario>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                usuarios = usuariosRepository.getAllUsuarios()
            } catch (e: Exception) {
                Log.e("UsuariosScreen", "Error al cargar usuarios", e)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Usuarios", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            try {
                Log.d("UsuariosScreen", "Navegando a usuarios_agregar_editar/null")
                navController.navigate("usuarios_agregar_editar/null")
            } catch (e: Exception) {
                Log.e("UsuariosScreen", "Error al navegar", e)
            }
        }) {
            Text("Agregar Usuario")
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(usuarios) { usuario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("ID: ${usuario.id_usuario}")
                        Text("Nombre: ${usuario.nombre1} ${usuario.nombre2 ?: ""} ${usuario.apellido1} ${usuario.apellido2 ?: ""}")
                        Text("Email: ${usuario.email}")
                        Text("Teléfono: ${usuario.telefono}")
                    }
                    Row {
                        IconButton(onClick = {
                            try {
                                Log.d("UsuariosScreen", "Navegando a usuarios_agregar_editar/${usuario.id_usuario}")
                                navController.navigate("usuarios_agregar_editar/${usuario.id_usuario}")
                            } catch (e: Exception) {
                                Log.e("UsuariosScreen", "Error al navegar", e)
                            }
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = {
                            scope.launch {
                                try {
                                    usuariosRepository.delete(usuario)
                                    usuarios = usuariosRepository.getAllUsuarios()
                                } catch (e: Exception) {
                                    Log.e("UsuariosScreen", "Error al borrar usuario", e)
                                }
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Borrar")
                        }
                    }
                }
            }
        }
    }
}
