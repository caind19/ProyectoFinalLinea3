package com.example.proyecto3corte.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medicamentos")
data class Medicamento(
    @PrimaryKey(autoGenerate = true) val id_medicamento: Int = 0,
    val nombre: String,
    val descripcion: String,
    val fabricante: String
)
