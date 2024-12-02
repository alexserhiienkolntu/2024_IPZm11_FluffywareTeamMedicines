package ua.edu.lntu.fluffywareteam.medicines.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard

@Dao
interface NotificationCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notificationCard: NotificationCard)

    @Delete
    suspend fun deleteNotification(notificationCard: NotificationCard)

    @Query("SELECT * FROM notification_cards")
    fun getAllNotifications(): Flow<List<NotificationCard>>

    @Query("SELECT * FROM notification_cards WHERE id = :id")
    suspend fun getNotificationById(id: Int): NotificationCard?
}
