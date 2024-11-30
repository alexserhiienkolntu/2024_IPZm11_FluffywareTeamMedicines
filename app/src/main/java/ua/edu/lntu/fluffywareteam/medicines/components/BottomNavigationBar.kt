// File: ./components/BottomNavigationBar.kt
package ua.edu.lntu.fluffywareteam.medicines.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ua.edu.lntu.fluffywareteam.medicines.stack.homeStack
import ua.edu.lntu.fluffywareteam.medicines.stack.notificationStack

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = {
            navController.navigate(homeStack.savedRoute)
        }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color(0xFFB39DDB)
            )
        }
        IconButton(onClick = { navController.navigate(notificationStack.savedRoute) }) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Notifications",
                tint = Color(0xFFB39DDB)
            )
        }
        IconButton(onClick = { navController.navigate("settings") }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color(0xFFB39DDB)
            )
        }
        IconButton(onClick = { navController.navigate("about") }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color(0xFFB39DDB)
            )
        }
    }
}
