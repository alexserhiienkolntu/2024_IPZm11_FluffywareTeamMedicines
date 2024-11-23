package ua.edu.lntu.fluffywareteam.medicines.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ua.edu.lntu.fluffywareteam.medicines.components.CreateMedicineButton
import ua.edu.lntu.fluffywareteam.medicines.components.MedicineItem
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine
import ua.edu.lntu.fluffywareteam.medicines.rooms.IMedicineDatabase

@Composable
fun HomeScreen(navController: NavHostController, database: IMedicineDatabase) {

    val medicineDao = database.medicineDao()
    val scope = rememberCoroutineScope()
    var medicines by remember { mutableStateOf(listOf<Medicine>()) }

    // Loading data from the database
    LaunchedEffect(Unit) {
        scope.launch {
            medicines = medicineDao.getAllMedicines()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Text
        Text(
            text = "Всі ліки",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CreateMedicineButton(navController)

        // We display a list of medicines
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(medicines) { medicine ->
                MedicineItem(
                    medicine = medicine,
                    onDelete = { selectedMedicine ->
                        scope.launch {
                            medicineDao.deleteMedicine(selectedMedicine)
                            medicines = medicineDao.getAllMedicines() // Refreshing the list after deletion
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}