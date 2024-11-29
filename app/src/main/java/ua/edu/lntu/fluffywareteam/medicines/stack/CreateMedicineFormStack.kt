package ua.edu.lntu.fluffywareteam.medicines.stack

class CreateMedicineFormStack(
    var medicineName: String = "",
    var medicineType: String = "",
    var whenToUse: String = "",
    var whenNotToUse: String = "",
    var additionalNotes: String = "",
) {
    fun set(
        medicineName: String? = null,
        medicineType: String? = null,
        whenToUse: String? = null,
        whenNotToUse: String? = null,
        additionalNotes: String? = null
    ) {
        if (medicineName != null) {
            this.medicineName = medicineName
        }
        if (medicineType != null) {
            this.medicineType = medicineType
        }
        if (whenToUse != null) {
            this.whenToUse = whenToUse
        }
        if (whenNotToUse != null) {
            this.whenNotToUse = whenNotToUse
        }
        if (additionalNotes != null) {
            this.additionalNotes = additionalNotes
        }
    }

    fun clear() {
        medicineName = ""
        medicineType = ""
        whenToUse = ""
        whenNotToUse = ""
        additionalNotes = ""
    }
}
