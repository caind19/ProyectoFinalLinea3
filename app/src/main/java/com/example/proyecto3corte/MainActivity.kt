package com.example.proyecto3corte

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyecto3corte.DAO.MedicamentosDao
import com.example.proyecto3corte.DAO.RecordatoriosDao
import com.example.proyecto3corte.DAO.UsuariosDao
import com.example.proyecto3corte.DAO.UsuariosMedicamentosDao
import com.example.proyecto3corte.Database.AppDatabase
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Repository.UsuariosMedicamentosRepository
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Screen.Navegacion
import com.example.proyecto3corte.ui.theme.Proyecto3CorteTheme

class MainActivity : ComponentActivity() {
    private lateinit var usuariosDao: UsuariosDao
    private lateinit var medicamentosDao: MedicamentosDao
    private lateinit var usuariosMedicamentosDao: UsuariosMedicamentosDao
    private lateinit var recordatoriosDao: RecordatoriosDao
    private lateinit var usuariosRepository: UsuariosRepository
    private lateinit var medicamentosRepository: MedicamentosRepository
    private lateinit var usuariosMedicamentosRepository: UsuariosMedicamentosRepository
    private lateinit var recordatoriosRepository: RecordatoriosRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(applicationContext)
        usuariosDao = db.usuariosDao()
        medicamentosDao = db.medicamentosDao()
        usuariosMedicamentosDao = db.usuariosMedicamentosDao()
        recordatoriosDao = db.recordatoriosDao()
        usuariosRepository = UsuariosRepository(usuariosDao)
        medicamentosRepository = MedicamentosRepository(medicamentosDao)
        usuariosMedicamentosRepository = UsuariosMedicamentosRepository(usuariosMedicamentosDao)
        recordatoriosRepository = RecordatoriosRepository(recordatoriosDao)

        enableEdgeToEdge()
        setContent {
            Proyecto3CorteTheme {
                Navegacion(
                    usuariosRepository,
                    medicamentosRepository,
                    usuariosMedicamentosRepository,
                    recordatoriosRepository
                )
            }
        }
    }
}
