package com.example.proyecto3corte.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF3EADCF), Color(0xFFABE9CD))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Título Principal
                Text(
                    text = "APP RecordaMedi",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3EADCF)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Subtítulo
                Text(
                    text = "Bienvenido a su app para recordar medicamentos",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Botones de Navegación
                Button(
                    onClick = { navController.navigate("usuarios") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3EADCF))
                ) {
                    Text("Usuarios", color = Color.White)
                }

                Button(
                    onClick = { navController.navigate("medicamentos") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3EADCF))
                ) {
                    Text("Medicamentos", color = Color.White)
                }

                Button(
                    onClick = { navController.navigate("recordatorios") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3EADCF))
                ) {
                    Text("Recordatorios", color = Color.White)
                }
            }
        }
    }
}
