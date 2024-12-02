package ua.edu.lntu.fluffywareteam.medicines.repositories

import ua.edu.lntu.fluffywareteam.medicines.dao.NotificationCardDao
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard

class NotificationRepository(private val dao: NotificationCardDao) {
    fun getAllNotifications() = dao.getAllNotifications()

    suspend fun addNotification(notificationCard: NotificationCard) {
        dao.insertNotification(notificationCard)
    }

    suspend fun deleteNotification(notificationCard: NotificationCard) {
        dao.deleteNotification(notificationCard)
    }
}

