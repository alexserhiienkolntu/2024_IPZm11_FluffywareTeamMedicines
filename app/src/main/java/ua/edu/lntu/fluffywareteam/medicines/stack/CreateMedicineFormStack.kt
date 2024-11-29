package ua.edu.lntu.fluffywareteam.medicines.stack

class CreateMedicineFormStack(
    var medicineName: String = "",
    var whenToUse: String = "",
    var whenNotToUse: String = "",
    var additionalNotes: String = "",
) {
    fun clear() {
        medicineName = ""
        whenToUse = ""
        whenNotToUse = ""
        additionalNotes = ""
    }
}
