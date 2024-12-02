package ua.edu.lntu.fluffywareteam.medicines.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.app.TimePickerDialog
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun CreateNotificationScreen(
    medicineList: List<String>,
    onSave: (String, String, List<String>) -> Unit
) {
    var selectedMedicine by rememberSaveable { mutableStateOf("") }
    var selectedTime by rememberSaveable { mutableStateOf("") }
    val daysOfWeek = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд")
    val selectedDays = remember { mutableStateMapOf<String, Boolean>().apply {
        daysOfWeek.forEach { put(it, false) }
    } }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Select medicine
        Text("Выберите Ліки:")
        DropdownMenu(
            selectedMedicine,
            medicineList,
            onMedicineSelected = { selectedMedicine = it }
        )

        // Input time
        Button(onClick = {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    selectedTime = String.format("%02d:%02d", hour, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }) {
            Text(if (selectedTime.isEmpty()) "Выбрать время" else "Время: $selectedTime")
        }

        // Select days
        Text("Выберите дни недели:")
        Column {
            daysOfWeek.forEach { day ->
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = selectedDays[day] ?: false,
                        onCheckedChange = { isChecked -> selectedDays[day] = isChecked }
                    )
                    Text(text = day)
                }
            }
        }

        // Save button
        Button(
            onClick = {
                val selectedDaysList = selectedDays.filterValues { it }.keys.toList()
                onSave(selectedMedicine, selectedTime, selectedDaysList)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Сохранить")
        }

        // Cancel
        Button(
            onClick = {
                val selectedDaysList = selectedDays.filterValues { it }.keys.toList()
                onSave(selectedMedicine, selectedTime, selectedDaysList)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Скасувати")
        }
    }
}

@Composable
fun DropdownMenu(
    selectedMedicine: String,
    medicineList: List<String>,
    onMedicineSelected: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedMedicine,
            onValueChange = { },
            readOnly = true,
            label = { Text("Ліки") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = androidx.compose.material.icons.Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            medicineList.forEach { medicine ->
                DropdownMenuItem(
                    onClick = {
                        onMedicineSelected(medicine)
                        expanded = false
                    },
                    text = { Text(text = medicine) }
                )
            }
        }
    }
}
