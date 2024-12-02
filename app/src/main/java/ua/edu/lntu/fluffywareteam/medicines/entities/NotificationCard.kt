package ua.edu.lntu.fluffywareteam.medicines.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_cards")
data class NotificationCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medicineName: String,
    val time: String, // Format - HH:mm
    val days: String, // A serialized list of days, for example: "Пн,Вт,Ср"
)
