// File: ./entities/Medicine.kt
package ua.edu.lntu.fluffywareteam.medicines.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageUri: String?,
    val type: String,
    val whenToUse: String,
    val whenNotToUse: String,
    val additionalNotes: String
)
