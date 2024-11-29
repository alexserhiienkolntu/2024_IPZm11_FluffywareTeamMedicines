// File: ./rooms/MedicineDatabase.ktерехід на архітектуру MVVM. (https://trello.com/c/ydMmRKGc/10-%D0%B7%D1%80%D0%BE%D0%B1%D0%B8%D1%82%D0%B8-%D0%BF%D0%B5%D1%80%D0%B5%D1%85%D1%96%D0%B4-%D0%BD%D0%B0-%D0%B0%D1%80%D1%85%D1%96%D1%82%D0%B5%D0%BA%D1%82%D1%83%D1%80%D1%83-mvvm)
package ua.edu.lntu.fluffywareteam.medicines.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.edu.lntu.fluffywareteam.medicines.dao.MedicineDao
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase(), IMedicineDatabase {
    abstract override fun medicineDao(): MedicineDao
}
