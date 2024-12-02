package ua.edu.lntu.fluffywareteam.medicines.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.edu.lntu.fluffywareteam.medicines.dao.NotificationCardDao
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard

@Database(entities = [NotificationCard::class], version = 1, exportSchema = false)
abstract class NotificationDatabase : RoomDatabase() {
    abstract fun notificationCardDao(): NotificationCardDao
}
