package ua.edu.lntu.fluffywareteam.medicines.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine
import ua.edu.lntu.fluffywareteam.medicines.rooms.IMedicineDatabase

@Composable
fun CreateMedicineScreen(database: IMedicineDatabase, onMedicineAdded: () -> Unit) {
    var medicineName by remember { mutableStateOf("") }
    var medicineImageUri by remember { mutableStateOf<Uri?>(null) }
    var medicineType by remember { mutableStateOf("") }
    var whenToUse by remember { mutableStateOf("") }
    var whenNotToUse by remember { mutableStateOf("") }
    var additionalNotes by remember { mutableStateOf("") }
    val medicineTypes = listOf("Таблетки", "Капсули", "Сироп", "Інше")
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val medicineDao = database.medicineDao()
    val scope = rememberCoroutineScope()

    // Launcher для выбора изображения
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        medicineImageUri = uri
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = "Додати ліки",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Назва ліків
        OutlinedTextField(
            value = medicineName,
            onValueChange = { medicineName = it },
            label = { Text("Назва ліків") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Зображення ліків
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Оберіть зображення")
        }
        Spacer(modifier = Modifier.height(8.dp))
        medicineImageUri?.let { uri ->
            Text(
                text = "Обране зображення: ${uri.lastPathSegment}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Тип ліків
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = medicineType,
                onValueChange = { },
                label = { Text("Тип ліків") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { isDropdownExpanded = true }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Dropdown")
                    }
                }
            )
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                medicineTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            medicineType = type
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // У яких випадках приймати
        OutlinedTextField(
            value = whenToUse,
            onValueChange = { whenToUse = it },
            label = { Text("У яких випадках приймати") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // У яких випадках НЕ приймати
        OutlinedTextField(
            value = whenNotToUse,
            onValueChange = { whenNotToUse = it },
            label = { Text("У яких випадках НЕ приймати") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Додаткові замітки (Text Area с прокруткой)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .verticalScroll(rememberScrollState())
                .padding(4.dp)
                .background(Color(0xFFF1F1F1), shape = MaterialTheme.shapes.medium)
        ) {
            BasicTextField(
                value = additionalNotes,
                onValueChange = { additionalNotes = it },
                textStyle = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                decorationBox = { innerTextField ->
                    if (additionalNotes.isEmpty()) {
                        Text(
                            text = "Додаткові замітки...",
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Додати"
        Button(
            onClick = {
                if (medicineName.isNotEmpty()) {
                    scope.launch {
                        medicineDao.insertMedicine(
                            Medicine(
                                name = medicineName,
                                imageUri = medicineImageUri?.toString(),
                                type = medicineType,
                                whenToUse = whenToUse,
                                whenNotToUse = whenNotToUse,
                                additionalNotes = additionalNotes
                            )
                        )
                        onMedicineAdded() // Переход к HomeScreen
                    }
                } else {
                    // Показываем сообщение об ошибке
                    Toast.makeText(
                        context,
                        "Назва ліків обов'язкове!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Додати ліки")
        }
    }
}