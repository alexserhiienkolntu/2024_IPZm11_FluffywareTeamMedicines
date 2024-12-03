// File: ./screens/CreateMedicineScreen.kt
package ua.edu.lntu.fluffywareteam.medicines.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine
import ua.edu.lntu.fluffywareteam.medicines.stack.medicineFormStack
import ua.edu.lntu.fluffywareteam.medicines.stack.homeStack
import ua.edu.lntu.fluffywareteam.medicines.utils.Alert
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.MedicineViewModel

@Composable
fun CreateMedicineScreen(viewModel: MedicineViewModel, navController: NavHostController, onMedicineAdded: () -> Unit) {
    var medicineName by rememberSaveable { mutableStateOf("") }
    var medicineImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var medicineType by rememberSaveable { mutableStateOf("") }
    var whenToUse by rememberSaveable { mutableStateOf("") }
    var whenNotToUse by rememberSaveable { mutableStateOf("") }
    var additionalNotes by rememberSaveable { mutableStateOf("") }

    medicineName = medicineFormStack.medicineName
    medicineType = medicineFormStack.medicineType
    whenToUse = medicineFormStack.whenToUse
    whenNotToUse = medicineFormStack.whenNotToUse
    additionalNotes = medicineFormStack.additionalNotes

    val scope = rememberCoroutineScope()

    // Launcher for choosing an image
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            medicineImageUri = uri
        }
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Додати ліки",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Назва ліків
        OutlinedTextField(
            value = medicineName,
            onValueChange = {
                medicineName = it
                medicineFormStack.set(medicineName = it)
            },
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
        OutlinedTextField(
            value = medicineType,
            onValueChange = {
                medicineType = it
                medicineFormStack.set(medicineType = it)
            },
            label = { Text("Тип ліків") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // У яких випадках приймати
        OutlinedTextField(
            value = whenToUse,
            onValueChange = {
                whenToUse = it
                medicineFormStack.set(whenToUse = it)
            },
            label = { Text("У яких випадках приймати") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // У яких випадках НЕ приймати
        OutlinedTextField(
            value = whenNotToUse,
            onValueChange = {
                whenNotToUse = it
                medicineFormStack.set(whenNotToUse = it)
            },
            label = { Text("У яких випадках НЕ приймати") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Додаткові замітки (Text Area)
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
                onValueChange = {
                    additionalNotes = it
                    medicineFormStack.set(additionalNotes = it)
                },
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

        // The Button "Додати"
        Button(
            onClick = {
                if (medicineName.isNotEmpty()) {
                    scope.launch {
                        viewModel.addMedicine(
                            Medicine(
                                name = medicineName,
                                imageUri = medicineImageUri?.toString(),
                                type = medicineType,
                                whenToUse = whenToUse,
                                whenNotToUse = whenNotToUse,
                                additionalNotes = additionalNotes
                            )
                        )
                        homeStack.savedRoute = "home"
                        medicineFormStack.clear()
                        onMedicineAdded() // Go to HomeScreen
                    }
                } else {
                    // Showing an error message
                    Alert.show(context, text="Назва ліків обов'язкове!")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Додати ліки")
        }

        Button(
            onClick = {
                homeStack.savedRoute = "home"
                navController.navigate("home")
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Скасувати")
        }
    }
}