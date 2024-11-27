// File: ./components/CreateMedicineButton.kt
package ua.edu.lntu.fluffywareteam.medicines.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CreateMedicineButton(navController: NavHostController) {

    Button(
        onClick = {
            navController.navigate("create-medicine-screen")
        },
        modifier = Modifier
            .padding(16.dp)
            .background(Color(0xFFB39DDB), RoundedCornerShape(8.dp))
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB39DDB),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Додати",
            fontSize = 24.sp,
            color = Color.Black
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.Black
        )
    }
}
