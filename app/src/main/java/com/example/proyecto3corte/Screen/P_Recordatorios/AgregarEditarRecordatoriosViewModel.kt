package com.example.proyecto3corte.Screen.P_Recordatorios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Model.Recordatorio
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

class AgregarEditarRecordatoriosViewModel(private val recordatoriosRepository: RecordatoriosRepository) : ViewModel() {
    var recordatorio = Recordatorio(
        id_recordatorio = 0,
        id_usuario = 0,
        id_medicamento = 0,
        fecha_recordatorio = LocalDate.now(),
        hora_recordatorio = LocalDateTime.now(),
        esRecurrente = false,
        notificado = false,
        observaciones = null,
        enviado = false, // Inicializar el campo agregado
        estado_alarma = false // Inicializar el campo agregado
    )
        private set

    fun loadRecordatorio(id: Int) {
        viewModelScope.launch {
            recordatorio = recordatoriosRepository.getRecordatorioById(id) ?: recordatorio
        }
    }

    fun onIdUsuarioChanged(idUsuario: Int) {
        recordatorio = recordatorio.copy(id_usuario = idUsuario)
    }

    fun onIdMedicamentoChanged(idMedicamento: Int) {
        recordatorio = recordatorio.copy(id_medicamento = idMedicamento)
    }

    fun onFechaRecordatorioChanged(fecha: LocalDate) {
        recordatorio = recordatorio.copy(fecha_recordatorio = fecha)
    }

    fun onHoraRecordatorioChanged(hora: LocalDateTime) {
        recordatorio = recordatorio.copy(hora_recordatorio = hora)
    }

    fun onEsRecurrenteChanged(esRecurrente: Boolean) {
        recordatorio = recordatorio.copy(esRecurrente = esRecurrente)
    }

    fun onNotificadoChanged(notificado: Boolean) {
        recordatorio = recordatorio.copy(notificado = notificado)
    }

    fun onObservacionesChanged(observaciones: String?) {
        recordatorio = recordatorio.copy(observaciones = observaciones)
    }

    fun onEnviadoChanged(enviado: Boolean) {
        recordatorio = recordatorio.copy(enviado = enviado)
    }

    fun onEstadoAlarmaChanged(estadoAlarma: Boolean) {
        recordatorio = recordatorio.copy(estado_alarma = estadoAlarma)
    }

    fun saveRecordatorio() {
        viewModelScope.launch {
            if (recordatorio.id_recordatorio == 0) {
                recordatoriosRepository.insert(recordatorio)
            } else {
                recordatoriosRepository.update(recordatorio)
            }
        }
    }
}
