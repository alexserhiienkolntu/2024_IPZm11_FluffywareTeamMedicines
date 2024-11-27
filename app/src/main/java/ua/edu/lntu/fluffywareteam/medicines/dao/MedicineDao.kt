// File: ./dao/MedicineDao.kt
package ua.edu.lntu.fluffywareteam.medicines.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

@Dao
interface MedicineDao {
    @Insert
    suspend fun insertMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): Flow<List<Medicine>>
}
