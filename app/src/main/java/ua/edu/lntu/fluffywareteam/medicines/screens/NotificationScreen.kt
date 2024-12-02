// File: ./screens/TimelineScreen.kt
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
import ua.edu.lntu.fluffywareteam.medicines.components.CreateNotificationButton
import ua.edu.lntu.fluffywareteam.medicines.components.NotificationItem
import ua.edu.lntu.fluffywareteam.medicines.viewmodels.NotificationViewModel

@Composable
fun NotificationScreen(navController: NavHostController, viewModel: NotificationViewModel) {
    val notificationList = viewModel.notifications.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Text
        Text(
            text = "Всі нагадування",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CreateNotificationButton(navController)

        // We display a list of medicines
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(notificationList.value) { notification ->
                NotificationItem(
                    notification = notification,
                    onDelete = { selectedNotification ->
                        viewModel.deleteNotification(selectedNotification) // Control via ViewModel
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
