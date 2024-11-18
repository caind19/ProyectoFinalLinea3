package com.example.proyecto3corte.Screen.P_Medicamentos

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
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Model.Medicamento
import kotlinx.coroutines.launch

@Composable
fun MedicamentosScreen(navController: NavController, medicamentosRepository: MedicamentosRepository) {
    var medicamentos by remember { mutableStateOf(listOf<Medicamento>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                medicamentos = medicamentosRepository.getAllMedicamentos()
            } catch (e: Exception) {
                Log.e("MedicamentosScreen", "Error al cargar medicamentos", e)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Medicamentos", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            try {
                Log.d("MedicamentosScreen", "Navegando a medicamentos_agregar_editar/null")
                navController.navigate("medicamentos_agregar_editar/null")
            } catch (e: Exception) {
                Log.e("MedicamentosScreen", "Error al navegar", e)
            }
        }) {
            Text("Agregar Medicamento")
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(medicamentos) { medicamento ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("ID: ${medicamento.id_medicamento}")
                        Text("Nombre: ${medicamento.nombre}")
                        Text("Descripción: ${medicamento.descripcion}")
                        Text("Fabricante: ${medicamento.fabricante}")
                    }
                    Row {
                        IconButton(onClick = {
                            try {
                                Log.d("MedicamentosScreen", "Navegando a medicamentos_agregar_editar/${medicamento.id_medicamento}")
                                navController.navigate("medicamentos_agregar_editar/${medicamento.id_medicamento}")
                            } catch (e: Exception) {
                                Log.e("MedicamentosScreen", "Error al navegar", e)
                            }
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = {
                            scope.launch {
                                try {
                                    medicamentosRepository.delete(medicamento)
                                    medicamentos = medicamentosRepository.getAllMedicamentos()
                                } catch (e: Exception) {
                                    Log.e("MedicamentosScreen", "Error al borrar medicamento", e)
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
