package ua.edu.lntu.fluffywareteam.medicines.rooms

import ua.edu.lntu.fluffywareteam.medicines.dao.MedicineDao

interface IMedicineDatabase  {
    fun medicineDao(): MedicineDao
}