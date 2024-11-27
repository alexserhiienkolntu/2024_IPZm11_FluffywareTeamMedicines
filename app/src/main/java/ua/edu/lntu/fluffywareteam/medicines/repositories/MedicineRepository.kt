package ua.edu.lntu.fluffywareteam.medicines.repositories

import kotlinx.coroutines.flow.Flow
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine
import ua.edu.lntu.fluffywareteam.medicines.rooms.MedicineDatabase

class MedicineRepository(database: MedicineDatabase) {

    private val dao = database.medicineDao()

    suspend fun addMedicine(medicine: Medicine) {
        dao.insertMedicine(medicine)
    }

    fun getAllMedicines(): Flow<List<Medicine>> {
        return dao.getAllMedicines()
    }

    suspend fun deleteMedicine(medicine: Medicine) {
        dao.deleteMedicine(medicine)
    }
}

