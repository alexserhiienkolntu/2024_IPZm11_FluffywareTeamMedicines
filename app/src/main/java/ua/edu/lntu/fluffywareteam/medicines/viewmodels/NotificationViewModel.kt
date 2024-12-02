package ua.edu.lntu.fluffywareteam.medicines.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard
import ua.edu.lntu.fluffywareteam.medicines.repositories.NotificationRepository

class NotificationViewModel(private val repository: NotificationRepository) : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationCard>>(emptyList())
    val notifications: StateFlow<List<NotificationCard>> get() = _notifications

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            repository.getAllNotifications().collect { notificationList ->
                _notifications.value = notificationList
            }
        }
    }

    fun addNotification(notificationCard: NotificationCard) {
        viewModelScope.launch {
            repository.addNotification(notificationCard)
        }
    }

    fun deleteNotification(notificationCard: NotificationCard) {
        viewModelScope.launch {
            repository.deleteNotification(notificationCard)
        }
    }
}
