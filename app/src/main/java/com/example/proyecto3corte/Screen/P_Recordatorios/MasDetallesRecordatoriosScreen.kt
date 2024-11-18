package com.example.proyecto3corte.Screen.P_Recordatorios

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto3corte.Model.Recordatorio
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun MasDetallesRecordatoriosScreen(
    navController: NavController,
    recordatorioId: Int,
    recordatoriosRepository: RecordatoriosRepository
) {
    var recordatorio by remember { mutableStateOf<Recordatorio?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(recordatorioId) {
        scope.launch {
            recordatorio = recordatoriosRepository.getRecordatorioById(recordatorioId)
        }
    }

    recordatorio?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Detalles del Recordatorio", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text("ID: ${it.id_recordatorio}")
            Text("ID Usuario: ${it.id_usuario}")
            Text("ID Medicamento: ${it.id_medicamento}")
            Text("Fecha de Recordatorio: ${it.fecha_recordatorio}")
            Text("Hora de Recordatorio: ${it.hora_recordatorio}")
            Text("Enviado: ${it.enviado}")
            Text("Estado de Alarma: ${it.estado_alarma}")
            Text("Fecha y Hora de Despacho: ${it.fecha_hora_despacho}")

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
            Text("Cargando detalles del recordatorio...")
        }
    }
}
