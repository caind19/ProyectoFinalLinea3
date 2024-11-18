package com.example.proyecto3corte.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.proyecto3corte.DAO.*
import com.example.proyecto3corte.Model.*

@Database(
    entities = [Usuario::class, Medicamento::class, UsuarioMedicamento::class, Recordatorio::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao
    abstract fun medicamentosDao(): MedicamentosDao
    abstract fun usuariosMedicamentosDao(): UsuariosMedicamentosDao
    abstract fun recordatoriosDao(): RecordatoriosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
