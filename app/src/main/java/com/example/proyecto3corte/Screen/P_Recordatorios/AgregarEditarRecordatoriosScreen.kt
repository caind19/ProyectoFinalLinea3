package com.example.proyecto3corte.Screen.P_Recordatorios

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
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Model.Recordatorio
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun AgregarEditarRecordatoriosScreen(
    navController: NavController,
    recordatorioId: Int?,
    recordatoriosRepository: RecordatoriosRepository
) {
    var recordatorio by remember {
        mutableStateOf(
            Recordatorio(
                id_recordatorio = 0,
                id_usuario = 0,
                id_medicamento = 0,
                fecha_recordatorio = LocalDate.now(),
                hora_recordatorio = LocalDateTime.now(),
                esRecurrente = false,
                notificado = false,
                observaciones = null,
                enviado = false,
                estado_alarma = false
            )
        )
    }
    val scope = rememberCoroutineScope()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(recordatorioId) {
        if (recordatorioId != null && recordatorioId != 0) {
            recordatorio = recordatoriosRepository.getRecordatorioById(recordatorioId) ?: recordatorio
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Agregar/Editar Recordatorio", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        TextField(
            value = recordatorio.fecha_recordatorio.format(dateFormatter),
            onValueChange = {
                try {
                    recordatorio = recordatorio.copy(fecha_recordatorio = LocalDate.parse(it, dateFormatter))
                    errorMessage = null
                } catch (e: DateTimeParseException) {
                    errorMessage = "Formato de fecha inválido. Use: yyyy-MM-dd"
                }
            },
            label = { Text("Fecha de Recordatorio (yyyy-MM-dd)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                if (recordatorio.id_usuario == 0 || recordatorio.id_medicamento == 0) {
                    errorMessage = "El ID de Usuario y el ID de Medicamento no pueden estar vacíos o en cero."
                    return@launch
                }

                try {
                    if (recordatorio.id_recordatorio == 0) {
                        recordatoriosRepository.insert(recordatorio)
                    } else {
                        recordatoriosRepository.update(recordatorio)
                    }
                    navController.popBackStack()
                } catch (e: Exception) {
                    errorMessage = "Error al guardar el recordatorio: ${e.localizedMessage}"
                }
            }
        }) {
            Text("Guardar")
        }
    }
}
