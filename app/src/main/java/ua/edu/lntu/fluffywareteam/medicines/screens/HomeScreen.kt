// File: ./screens/HomeScreen.kt
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.edu.lntu.fluffywareteam.medicines.components.CreateMedicineButton
import ua.edu.lntu.fluffywareteam.medicines.components.MedicineItem
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.MedicineViewModel
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.NotificationViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    medicineViewModel: MedicineViewModel,
    notificationViewModel: NotificationViewModel
) {

    val medicineList = medicineViewModel.medicineList.collectAsState().value
    val notificationList = notificationViewModel.notifications.collectAsState().value

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
            items(medicineList) { medicine ->
                MedicineItem(
                    medicine = medicine,
                    onDelete = { selectedMedicine ->
                        // Cascade delete
                        notificationList.forEach {notificationCard ->
                            if (notificationCard.medicineName == selectedMedicine.name) {
                                notificationViewModel.deleteNotification(notificationCard)
                            }
                        }
                        medicineViewModel.deleteMedicine(selectedMedicine) // Control via ViewModel
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}