package ua.edu.lntu.fluffywareteam.medicines.providers

import android.content.Context
import androidx.room.Room
import ua.edu.lntu.fluffywareteam.medicines.repositories.NotificationRepository
import ua.edu.lntu.fluffywareteam.medicines.rooms.NotificationDatabase

class NotificationRepositoryProvider {
    fun provideRepository(context: Context): NotificationRepository {
        val database = Room.databaseBuilder(
            context,
            NotificationDatabase::class.java,
            "notification_database"
        ).build()
        return NotificationRepository(database.notificationCardDao())
    }
}
