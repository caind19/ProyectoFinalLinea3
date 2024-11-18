package com.example.proyecto3corte.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0, // Generación automática
    val nombre1: String,
    val nombre2: String?,
    val apellido1: String,
    val apellido2: String?,
    val email: String,
    val telefono: String,
    val fecha_nacimiento: LocalDate
)
