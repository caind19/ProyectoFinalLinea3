package com.example.proyecto3corte.Screen.P_Medicamentos

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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
fun MasDetallesMedicamentosScreen(
    navController: NavController,
    medicamentoId: Int,
    medicamentosRepository: MedicamentosRepository
) {
    var medicamento by remember { mutableStateOf<Medicamento?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(medicamentoId) {
        scope.launch {
            medicamento = medicamentosRepository.getMedicamentoById(medicamentoId)
        }
    }

    medicamento?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Detalles del Medicamento", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text("ID: ${it.id_medicamento}")
            Text("Nombre: ${it.nombre}")
            Text("Descripción: ${it.descripcion}")
            Text("Fabricante: ${it.fabricante}")

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
            Text("Cargando detalles del medicamento...")
        }
    }
}
