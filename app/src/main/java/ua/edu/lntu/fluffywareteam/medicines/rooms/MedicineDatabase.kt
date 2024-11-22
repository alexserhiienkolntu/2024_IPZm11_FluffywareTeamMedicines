package ua.edu.lntu.fluffywareteam.medicines.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.edu.lntu.fluffywareteam.medicines.dao.MedicineDao
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase(), IMedicineDatabase {
    abstract override fun medicineDao(): MedicineDao

    companion object {
        @Volatile
        private var INSTANCE: MedicineDatabase? = null

        fun getDatabase(context: Context): MedicineDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicineDatabase::class.java,
                    "medicine_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
