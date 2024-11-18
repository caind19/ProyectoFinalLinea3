package com.example.proyecto3corte.Screen.P_Recordatorios

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
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Model.Recordatorio
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import android.util.Log

@Composable
fun RecordatoriosScreen(navController: NavController, recordatoriosRepository: RecordatoriosRepository) {
    var recordatorios by remember { mutableStateOf(listOf<Recordatorio>()) }
    val scope = rememberCoroutineScope()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    LaunchedEffect(Unit) {
        scope.launch {
            recordatorios = recordatoriosRepository.getAllRecordatorios()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Recordatorios", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            try {
                Log.d("RecordatoriosScreen", "Navegando a recordatorios_agregar_editar/null")
                navController.navigate("recordatorios_agregar_editar/null")
            } catch (e: Exception) {
                Log.e("RecordatoriosScreen", "Error al navegar", e)
            }
        }) {
            Text("Agregar Recordatorio")
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recordatorios) { recordatorio ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("ID: ${recordatorio.id_recordatorio}")
                        Text("Fecha: ${recordatorio.fecha_recordatorio.format(dateFormatter)}")
                        Text("Hora: ${recordatorio.hora_recordatorio.format(dateTimeFormatter)}")
                    }
                    Row {
                        IconButton(onClick = {
                            try {
                                Log.d("RecordatoriosScreen", "Navegando a recordatorios_agregar_editar/${recordatorio.id_recordatorio}")
                                navController.navigate("recordatorios_agregar_editar/${recordatorio.id_recordatorio}")
                            } catch (e: Exception) {
                                Log.e("RecordatoriosScreen", "Error al navegar", e)
                            }
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = {
                            scope.launch {
                                try {
                                    recordatoriosRepository.delete(recordatorio)
                                    recordatorios = recordatoriosRepository.getAllRecordatorios()
                                } catch (e: Exception) {
                                    Log.e("RecordatoriosScreen", "Error al borrar", e)
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