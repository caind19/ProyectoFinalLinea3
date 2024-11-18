# Medication Management App

## Descripción

La **Medication Management App** es una aplicación móvil desarrollada con Kotlin, Android Studio, Jetpack Compose, KSP (Kotlin Symbol Processing) y Room para gestionar el uso de medicamentos y usuarios. Está diseñada para ser utilizada por profesionales de la salud, como enfermeras, para administrar y recordar la toma de medicamentos de varios pacientes.

## Características

- **Gestión de Usuarios:** Agregar, editar, y visualizar detalles de usuarios.
- **Gestión de Medicamentos:** Agregar, editar, y visualizar detalles de medicamentos.
- **Gestión de Recordatorios:** Crear, editar, y gestionar recordatorios para la administración de medicamentos.
- **Notificaciones:** Envío de recordatorios vía correo electrónico y notificaciones tipo alarma que se activan incluso si el dispositivo está bloqueado o apagado.

## Estructura del Proyecto

src/ |-- DAO/ | |-- UsuariosDao.kt | |-- MedicamentosDao.kt | |-- UsuariosMedicamentosDao.kt | |-- RecordatoriosDao.kt |-- Database/ | |-- AppDatabase.kt | |-- DateTypeConverter.kt |-- Model/ | |-- Usuario.kt | |-- Medicamento.kt | |-- UsuarioMedicamento.kt | |-- Recordatorio.kt |-- Repository/ | |-- UsuariosRepository.kt | |-- MedicamentosRepository.kt | |-- UsuariosMedicamentosRepository.kt | |-- RecordatoriosRepository.kt |-- Screen/ | |-- HomeScreen.kt | |-- Navegacion.kt | |-- P_Usuarios/ | | |-- UsuariosViewModel.kt | | |-- MasDetallesUsuariosViewModel.kt | | |-- AgregarEditarUsuariosViewModel.kt | | |-- UsuariosScreen.kt | | |-- MasDetallesUsuariosScreen.kt | | |-- AgregarEditarUsuariosScreen.kt | |-- P_Medicamentos/ | | |-- MedicamentosViewModel.kt | | |-- MasDetallesMedicamentosViewModel.kt | | |-- AgregarEditarMedicamentosViewModel.kt | | |-- MedicamentosScreen.kt | | |-- MasDetallesMedicamentosScreen.kt | | |-- AgregarEditarMedicamentosScreen.kt | |-- P_Recordatorios/ | | |-- RecordatoriosViewModel.kt | | |-- MasDetallesRecordatoriosViewModel.kt | | |-- AgregarEditarRecordatoriosViewModel.kt | | |-- RecordatoriosScreen.kt | | |-- MasDetallesRecordatoriosScreen.kt | | |-- AgregarEditarRecordatoriosScreen.kt |-- ui.theme/ | |-- Color.kt | |-- Theme.kt | |-- Type.kt |-- MainActivity.kt

## Uso

1. Iniciar la aplicación.

2. Navegar a través del menú principal ("HomeScreen") para gestionar usuarios, medicamentos y recordatorios.

3. Configurar recordatorios y recibir notificaciones según la programación establecida.

## Elaborado por

- Michael Alexander Corredor Castillo
- Cain David Martinez