package com.example.proyecto3corte.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = "Recordatorios",
    foreignKeys = [
        ForeignKey(entity = Usuario::class, parentColumns = ["id_usuario"], childColumns = ["id_usuario"]),
        ForeignKey(entity = Medicamento::class, parentColumns = ["id_medicamento"], childColumns = ["id_medicamento"])
    ]
)
data class Recordatorio(
    @PrimaryKey(autoGenerate = true) val id_recordatorio: Int = 0,
    val id_usuario: Int,
    val id_medicamento: Int,
    val fecha_recordatorio: LocalDate,
    val hora_recordatorio: LocalDateTime,
    val esRecurrente: Boolean = false,
    val notificado: Boolean = false,
    val observaciones: String? = null,
    val enviado: Boolean = false,
    val estado_alarma: Boolean = false,
    val fecha_hora_despacho: LocalDateTime? = null // Campo agregado
)

