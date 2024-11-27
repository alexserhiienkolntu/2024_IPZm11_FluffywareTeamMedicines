package ua.edu.lntu.fluffywareteam.medicines.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.edu.lntu.fluffywareteam.medicines.entities.Medicine
import ua.edu.lntu.fluffywareteam.medicines.repositories.MedicineRepository

class MedicineViewModel(private val repository: MedicineRepository) : ViewModel() {
    private val _medicineList = MutableStateFlow<List<Medicine>>(emptyList())
    val medicineList: StateFlow<List<Medicine>> get() = _medicineList

    init {
        loadMedicines()
    }

    private fun loadMedicines() {
        viewModelScope.launch {
            repository.getAllMedicines().collect { medicines ->
                _medicineList.value = medicines
            }
        }
    }

    fun addMedicine(medicine: Medicine) {
        viewModelScope.launch {
            repository.addMedicine(medicine)
            loadMedicines()
        }
    }

    fun deleteMedicine(medicine: Medicine) {
        viewModelScope.launch {
            repository.deleteMedicine(medicine)
            loadMedicines()
        }
    }
}
