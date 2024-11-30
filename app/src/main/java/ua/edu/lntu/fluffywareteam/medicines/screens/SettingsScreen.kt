// File: ./screens/SettingsScreen.kt
package ua.edu.lntu.fluffywareteam.medicines.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edu.lntu.fluffywareteam.medicines.components.PasswordInputField
import ua.edu.lntu.fluffywareteam.medicines.stack.loginFormStack

@Composable
fun SettingsScreen() {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    login = loginFormStack.login
    password = loginFormStack.password

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Синхронізація даних",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Логін
        OutlinedTextField(
            value = login,
            onValueChange = {
                login = it
                loginFormStack.set(login = it)
            },
            label = { Text("Логін") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Пароль
        PasswordInputField(password, onPasswordChange = {
            password = it
            loginFormStack.set(password = it)
        })

        Spacer(modifier = Modifier.height(16.dp))

        // The Login Button
        Button(
            onClick = {
                loginFormStack.clear()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Авторизуватися")
        }
    }
}
