package com.example.proyecto3corte.Screen.P_Medicamentos

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
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Model.Medicamento
import kotlinx.coroutines.launch

@Composable
fun AgregarEditarMedicamentosScreen(
    navController: NavController,
    medicamentoId: Int?,
    medicamentosRepository: MedicamentosRepository
) {
    var medicamento by remember { mutableStateOf(Medicamento(0, "", "", "")) }
    val scope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(medicamentoId) {
        if (medicamentoId != null && medicamentoId != 0) {
            medicamento = medicamentosRepository.getMedicamentoById(medicamentoId) ?: medicamento
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Agregar/Editar Medicamento", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        TextField(
            value = medicamento.nombre,
            onValueChange = { medicamento = medicamento.copy(nombre = it) },
            label = { Text("Nombre del Medicamento") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = medicamento.descripcion,
            onValueChange = { medicamento = medicamento.copy(descripcion = it) },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = medicamento.fabricante,
            onValueChange = { medicamento = medicamento.copy(fabricante = it) },
            label = { Text("Fabricante") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                if (medicamento.nombre.isBlank() || medicamento.descripcion.isBlank()) {
                    errorMessage = "Los campos Nombre y Descripción son obligatorios."
                    return@launch
                }

                try {
                    if (medicamento.id_medicamento == 0) {
                        medicamentosRepository.insert(medicamento)
                    } else {
                        medicamentosRepository.update(medicamento)
                    }
                    navController.popBackStack()
                } catch (e: Exception) {
                    errorMessage = "Error al guardar el medicamento: ${e.localizedMessage}"
                }
            }
        }) {
            Text("Guardar")
        }
    }
}

