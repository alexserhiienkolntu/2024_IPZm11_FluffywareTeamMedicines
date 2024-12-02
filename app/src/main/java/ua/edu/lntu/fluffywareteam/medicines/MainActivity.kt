// File: ./MainActivity.kt
package ua.edu.lntu.fluffywareteam.medicines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.fluffywareteam.medicines.components.BottomNavigationBar
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard
import ua.edu.lntu.fluffywareteam.medicines.providers.MedicineRepositoryProvider
import ua.edu.lntu.fluffywareteam.medicines.providers.NotificationRepositoryProvider
import ua.edu.lntu.fluffywareteam.medicines.screens.AboutScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.HomeScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.SettingsScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.NotificationScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.CreateMedicineScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.CreateNotificationScreen
import ua.edu.lntu.fluffywareteam.medicines.stack.notificationStack
import ua.edu.lntu.fluffywareteam.medicines.ui.theme.FluffywareTeamMedicinesTheme
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.MedicineViewModel
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.NotificationViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val medicineProvider = MedicineRepositoryProvider()
        val medicineRepository = medicineProvider.provideRepository(context = this)
        val medicineViewModel = MedicineViewModel(medicineRepository)

        val notificationProvider = NotificationRepositoryProvider()
        val notificationRepository = notificationProvider.provideRepository(context = this)
        val notificationViewModel = NotificationViewModel(notificationRepository)

        setContent {
            FluffywareTeamMedicinesTheme {
                MedicinesApp(medicineViewModel=medicineViewModel, notificationViewModel=notificationViewModel)
            }
        }
    }
}

@Composable
fun MedicinesApp(medicineViewModel: MedicineViewModel, notificationViewModel: NotificationViewModel) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController = navController, viewModel = medicineViewModel)
            }
            composable("notifications") {
                NotificationScreen(navController = navController, viewModel = notificationViewModel)
            }
            composable("settings") { SettingsScreen() }
            composable("about") { AboutScreen() }
            composable("create-medicine") {
                CreateMedicineScreen(
                    viewModel = medicineViewModel,
                    navController=navController,
                    onMedicineAdded = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                        }
                    }
                )
            }
            composable("create-notification") {
                CreateNotificationScreen(
                    medicineList = listOf("Аспирин", "Парацетамол", "Ибупрофен"),
                    onSave = { selectedMedicine, time, selectedDays ->
                        val notificationCard = NotificationCard(
                            medicineName = selectedMedicine,
                            time = time,
                            days = selectedDays.joinToString(",") // Преобразуем дни в строку
                        )
                        notificationViewModel.addNotification(notificationCard)
                        notificationStack.savedRoute = "notifications"
                        navController.navigate("notifications")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicinesAppPreview() {

    val context = LocalContext.current
    val medicineProvider = MedicineRepositoryProvider()
    val medicineRepository = medicineProvider.provideRepository(context)
    val medicineViewModel = MedicineViewModel(medicineRepository)

    val notificationProvider = NotificationRepositoryProvider()
    val notificationRepository = notificationProvider.provideRepository(context)
    val notificationViewModel = NotificationViewModel(notificationRepository)

    FluffywareTeamMedicinesTheme {
        MedicinesApp(medicineViewModel=medicineViewModel, notificationViewModel=notificationViewModel)
    }
}
