package ua.edu.lntu.fluffywareteam.medicines.rooms

import ua.edu.lntu.fluffywareteam.medicines.dao.FakeMedicineDao
import ua.edu.lntu.fluffywareteam.medicines.dao.MedicineDao

class FakeMedicineDatabase : IMedicineDatabase {
    private val fakeDao = FakeMedicineDao()

    override fun medicineDao(): MedicineDao = fakeDao
}
