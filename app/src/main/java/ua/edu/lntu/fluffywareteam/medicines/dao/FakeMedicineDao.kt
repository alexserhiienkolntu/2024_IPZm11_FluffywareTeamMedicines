package ua.edu.lntu.fluffywareteam.medicines.dao

import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine

class FakeMedicineDao : MedicineDao {
    override suspend fun getAllMedicines(): List<Medicine> {
        return listOf(
            Medicine(
                name = "Аспірин",
                imageUri = null,
                type = "Таблетки",
                whenToUse = "При головному болі",
                whenNotToUse = "При алергії на аспірин",
                additionalNotes = "Зберігати в сухому місці"
            ),
            Medicine(
                name = "Парацетамол",
                imageUri = null,
                type = "Таблетки",
                whenToUse = "При температурі",
                whenNotToUse = "При захворюваннях печінки",
                additionalNotes = "Дозування за інструкцією"
            )
        )
    }

    override suspend fun insertMedicine(medicine: Medicine) {
        // Заглушка: ничего не делаем
    }

    override suspend fun deleteMedicine(medicine: Medicine) {
        // Заглушка: ничего не делаем
    }
}