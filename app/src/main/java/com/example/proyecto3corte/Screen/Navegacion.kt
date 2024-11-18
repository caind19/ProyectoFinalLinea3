package com.example.proyecto3corte.Screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto3corte.Repository.UsuariosRepository
import com.example.proyecto3corte.Repository.MedicamentosRepository
import com.example.proyecto3corte.Repository.UsuariosMedicamentosRepository
import com.example.proyecto3corte.Repository.RecordatoriosRepository
import com.example.proyecto3corte.Screen.P_Medicamentos.AgregarEditarMedicamentosScreen
import com.example.proyecto3corte.Screen.P_Medicamentos.MasDetallesMedicamentosScreen
import com.example.proyecto3corte.Screen.P_Medicamentos.MedicamentosScreen
import com.example.proyecto3corte.Screen.P_Recordatorios.AgregarEditarRecordatoriosScreen
import com.example.proyecto3corte.Screen.P_Recordatorios.MasDetallesRecordatoriosScreen
import com.example.proyecto3corte.Screen.P_Recordatorios.RecordatoriosScreen
import com.example.proyecto3corte.Screen.P_Usuarios.AgregarEditarUsuariosScreen
import com.example.proyecto3corte.Screen.P_Usuarios.MasDetallesUsuariosScreen
import com.example.proyecto3corte.Screen.P_Usuarios.UsuariosScreen

@Composable
fun Navegacion(
    usuariosRepository: UsuariosRepository,
    medicamentosRepository: MedicamentosRepository,
    usuariosMedicamentosRepository: UsuariosMedicamentosRepository,
    recordatoriosRepository: RecordatoriosRepository
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("usuarios") { UsuariosScreen(navController, usuariosRepository) }
        composable("medicamentos") { MedicamentosScreen(navController, medicamentosRepository) }
        composable("recordatorios") { RecordatoriosScreen(navController, recordatoriosRepository) }

        composable("usuarios_detalles/{usuarioId}") { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getString("usuarioId")?.toIntOrNull() ?: 0
            MasDetallesUsuariosScreen(navController, usuarioId, usuariosRepository)
        }
        composable("usuarios_agregar_editar/{usuarioId}") { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getString("usuarioId")?.toIntOrNull()
            AgregarEditarUsuariosScreen(navController, usuarioId, usuariosRepository)
        }
        composable("medicamentos_detalles/{medicamentoId}") { backStackEntry ->
            val medicamentoId = backStackEntry.arguments?.getString("medicamentoId")?.toIntOrNull() ?: 0
            MasDetallesMedicamentosScreen(navController, medicamentoId, medicamentosRepository)
        }
        composable("medicamentos_agregar_editar/{medicamentoId}") { backStackEntry ->
            val medicamentoId = backStackEntry.arguments?.getString("medicamentoId")?.toIntOrNull()
            AgregarEditarMedicamentosScreen(navController, medicamentoId, medicamentosRepository)
        }
        composable("recordatorios_detalles/{recordatorioId}") { backStackEntry ->
            val recordatorioId = backStackEntry.arguments?.getString("recordatorioId")?.toIntOrNull() ?: 0
            MasDetallesRecordatoriosScreen(navController, recordatorioId, recordatoriosRepository)
        }
        composable("recordatorios_agregar_editar/{recordatorioId}") { backStackEntry ->
            val recordatorioId = backStackEntry.arguments?.getString("recordatorioId")?.toIntOrNull()
            AgregarEditarRecordatoriosScreen(navController, recordatorioId, recordatoriosRepository)
        }
    }
}
