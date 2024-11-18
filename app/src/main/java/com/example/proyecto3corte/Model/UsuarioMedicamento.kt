package com.example.proyecto3corte.Model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "UsuarioMedicamento",
    primaryKeys = ["id_usuario", "id_medicamento"],
    foreignKeys = [
        ForeignKey(entity = Usuario::class, parentColumns = ["id_usuario"], childColumns = ["id_usuario"]),
        ForeignKey(entity = Medicamento::class, parentColumns = ["id_medicamento"], childColumns = ["id_medicamento"])
    ]
)
data class UsuarioMedicamento(
    val id_usuario: Int,
    val id_medicamento: Int,
    val dosis: String,
    val frecuencia: String,
    val hora_administracion: String // Consider using a more suitable data type if needed
)
