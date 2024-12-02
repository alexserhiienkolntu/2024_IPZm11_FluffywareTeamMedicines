package ua.edu.lntu.fluffywareteam.medicines.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard
@Composable
fun NotificationItem(
    notification: NotificationCard,
    onDelete: (NotificationCard) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left block with text information
            Column(
                modifier = Modifier.weight(1f) // Takes up all the remaining space
            ) {
                Text(text = "Назва ліків: ${notification.medicineName}", fontSize = 18.sp)
                Text(text = "Година: ${notification.time}", fontSize = 14.sp)
                Text(text = "Дні: ${notification.days}", fontSize = 14.sp)
            }

            // Button with basket
            IconButton(
                onClick = { onDelete(notification) } // Click handling
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, // Trash icon
                    contentDescription = "Видалити ліки",
                    tint = Color.Red // Icon color
                )
            }
        }
    }
}