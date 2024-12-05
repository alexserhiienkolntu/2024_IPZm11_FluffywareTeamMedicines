// File: ./screens/SettingsScreen.kt
package ua.edu.lntu.fluffywareteam.medicines.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ua.edu.lntu.fluffywareteam.medicines.forms.AuthForm
import ua.edu.lntu.fluffywareteam.medicines.preferences.UserPreferences

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val username = userPreferences.getUsername()
    // val token = userPreferences.getAccessToken()
    if (username == null) {
        AuthForm(context, navController, userPreferences)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Добрий день, $username!",
                style = MaterialTheme.typography.titleLarge
            )

            // Text(
            //     text = "Токен, $token!",
            //     style = MaterialTheme.typography.titleLarge
            // )

            Button(
                onClick = {
                    userPreferences.clearPreferences()
                    navController.navigate("settings")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Скасувати синхронизацію")
            }
        }

    }
}
