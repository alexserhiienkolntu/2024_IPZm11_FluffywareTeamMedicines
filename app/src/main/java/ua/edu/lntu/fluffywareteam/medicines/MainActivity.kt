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
import ua.edu.lntu.fluffywareteam.medicines.providers.MedicineRepositoryProvider
import ua.edu.lntu.fluffywareteam.medicines.screens.AboutScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.HomeScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.SettingsScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.TimelineScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.CreateMedicineScreen
import ua.edu.lntu.fluffywareteam.medicines.ui.theme.FluffywareTeamMedicinesTheme
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.MedicineViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val provider = MedicineRepositoryProvider()
        val repository = provider.provideRepository(this)
        val viewModel = MedicineViewModel(repository)

        setContent {
            FluffywareTeamMedicinesTheme {
                MedicinesApp(viewModel=viewModel)
            }
        }
    }
}

@Composable
fun MedicinesApp(viewModel: MedicineViewModel) {
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
            composable("home") { HomeScreen(navController, viewModel) }
            composable("timeline") { TimelineScreen() }
            composable("settings") { SettingsScreen() }
            composable("about") { AboutScreen() }
            composable("create-medicine-screen") {
                CreateMedicineScreen(viewModel, onMedicineAdded = {
                    navController.navigate("home")
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicinesAppPreview() {
    val context = LocalContext.current
    val provider = MedicineRepositoryProvider()
    val repository = provider.provideRepository(context)
    val viewModel = MedicineViewModel(repository)

    FluffywareTeamMedicinesTheme {
        MedicinesApp(viewModel=viewModel)
    }
}
