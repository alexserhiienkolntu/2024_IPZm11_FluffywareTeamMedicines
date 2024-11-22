package ua.edu.lntu.fluffywareteam.medicines.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query

import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

@Dao
interface MedicineDao {
    @Insert
    suspend fun insertMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicines")
    suspend fun getAllMedicines(): List<Medicine>
}
