// File: ./screens/AboutScreen.kt
package ua.edu.lntu.fluffywareteam.medicines.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ua.edu.lntu.fluffywareteam.medicines.utils.WebUtils

@Composable
fun AboutScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Назва додатка та версія
        Text(
            text = "Fluffyware Team Medicines",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Версія: 1.0.0",
            style = MaterialTheme.typography.titleLarge
        )

        // Автори
        Text(
            text = "Розробник: Олександр Сергієнко",
            style = MaterialTheme.typography.titleLarge
        )

        TextButton(
            onClick = { WebUtils.openWebsite(context, "https://github.com/alexserhiienkolntu/2024_IPZm11_FluffywareTeamMedicines") }
        ) {
            Text("GitHub репозиторій", style = MaterialTheme.typography.titleLarge)
        }
    }
}
