// File: ./rooms/MedicineDatabase.kt
package ua.edu.lntu.fluffywareteam.medicines.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.edu.lntu.fluffywareteam.medicines.dao.MedicineDao
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase(), IMedicineDatabase {
    abstract override fun medicineDao(): MedicineDao
}
