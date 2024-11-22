package ua.edu.lntu.fluffywareteam.medicines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.fluffywareteam.medicines.components.BottomNavigationBar
import ua.edu.lntu.fluffywareteam.medicines.rooms.FakeMedicineDatabase
import ua.edu.lntu.fluffywareteam.medicines.rooms.MedicineDatabase
import  ua.edu.lntu.fluffywareteam.medicines.rooms.IMedicineDatabase
import ua.edu.lntu.fluffywareteam.medicines.screens.AboutScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.HomeScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.SettingsScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.TimelineScreen
import ua.edu.lntu.fluffywareteam.medicines.screens.CreateMedicineScreen
import ua.edu.lntu.fluffywareteam.medicines.ui.theme.FluffywareTeamMedicinesTheme


class MainActivity : ComponentActivity() {
    private lateinit var database: MedicineDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = MedicineDatabase.getDatabase(applicationContext)
        setContent {
            FluffywareTeamMedicinesTheme {
                MedicinesApp(database=database)
            }
        }
    }
}

@Composable
fun MedicinesApp(database: IMedicineDatabase) {
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
            composable("home") { HomeScreen(navController, database) }
            composable("timeline") { TimelineScreen() }
            composable("settings") { SettingsScreen() }
            composable("about") { AboutScreen() }
            composable("create-medicine-screen") {
                CreateMedicineScreen(database, onMedicineAdded = {
                    navController.navigate("home")
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MedicinesAppPreview() {
    val fakeDatabase = FakeMedicineDatabase() // Используем заглушку

    FluffywareTeamMedicinesTheme {
        MedicinesApp(database = fakeDatabase)
    }
}
