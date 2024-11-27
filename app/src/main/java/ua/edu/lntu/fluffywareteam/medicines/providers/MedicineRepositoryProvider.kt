package ua.edu.lntu.fluffywareteam.medicines.providers

import android.content.Context
import androidx.room.Room
import ua.edu.lntu.fluffywareteam.medicines.repositories.MedicineRepository
import ua.edu.lntu.fluffywareteam.medicines.rooms.MedicineDatabase

class MedicineRepositoryProvider {

    fun provideRepository(context: Context): MedicineRepository {
        val database = Room.databaseBuilder(
            context,
            MedicineDatabase::class.java,
            "medicine_database"
        ).build()
        return MedicineRepository(database)
    }

}
