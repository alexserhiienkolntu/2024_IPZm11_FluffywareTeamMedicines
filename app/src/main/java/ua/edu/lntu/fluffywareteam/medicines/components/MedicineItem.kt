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
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

@Composable
fun MedicineItem(
    medicine: Medicine,
    onDelete: (Medicine) -> Unit // Функция для обработки удаления
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Центрирование по вертикали
        ) {
            // Левый блок с текстовой информацией
            Column(
                modifier = Modifier.weight(1f) // Забирает всё оставшееся пространство
            ) {
                Text(text = "Назва: ${medicine.name}", fontSize = 18.sp)
                medicine.imageUri?.let { uri ->
                    Text(text = "Зображення: $uri", fontSize = 14.sp, color = Color.Gray)
                }
                Text(text = "Тип: ${medicine.type}", fontSize = 14.sp)
                Text(text = "Приймати: ${medicine.whenToUse}", fontSize = 14.sp)
                Text(text = "Не приймати: ${medicine.whenNotToUse}", fontSize = 14.sp)
                Text(text = "Додаткові замітки: ${medicine.additionalNotes}", fontSize = 14.sp)
            }

            // Кнопка с корзиной
            IconButton(
                onClick = { onDelete(medicine) } // Обработка клика
            ) {
                Icon(
                    imageVector = Icons.Default.Delete, // Иконка корзины
                    contentDescription = "Удалить лекарство",
                    tint = Color.Red // Цвет иконки
                )
            }
        }
    }
}