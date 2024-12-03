package ua.edu.lntu.fluffywareteam.medicines.channels

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import ua.edu.lntu.fluffywareteam.medicines.entities.NotificationCard
import ua.edu.lntu.fluffywareteam.medicines.receivers.NotificationReceiver
import java.util.Calendar


class NotificationChannelService {
    companion object {
        fun createNotificationChannel(context: Context) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "medicine_channel",
                    "Нагадування о ліках",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Medication Reminder Channel"
                }

                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager?.createNotificationChannel(channel)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    Log.e("Notification", "Permission POST_NOTIFICATIONS not granted")
                    return
                }
            }

        }

        fun scheduleNotification(context: Context, card: NotificationCard) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val timeParts = card.time.split(":").map { it.toInt() }
            val hour = timeParts[0]
            val minute = timeParts[1]

            // Every day
            val days = if (card.days.isEmpty()) {
                listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд")
            } else {
                card.days.split(",").map { it.trim() }
            }

            // Для кожного вказаного дня
            days.forEach { day ->
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    this.set(Calendar.HOUR_OF_DAY, hour)
                    this.set(Calendar.MINUTE, minute)
                    this.set(Calendar.SECOND, 0)
                    this.set(Calendar.MILLISECOND, 0)

                    // Встановлення дня тижня
                    val dayOfWeek = when (day) {
                        "Пн" -> Calendar.MONDAY
                        "Вт" -> Calendar.TUESDAY
                        "Ср" -> Calendar.WEDNESDAY
                        "Чт" -> Calendar.THURSDAY
                        "Пт" -> Calendar.FRIDAY
                        "Сб" -> Calendar.SATURDAY
                        "Нд" -> Calendar.SUNDAY
                        else -> Calendar.MONDAY
                    }
                    this.set(Calendar.DAY_OF_WEEK, dayOfWeek)

                     // Якщо час уже минув сьогодні, плануємо на наступний тиждень
                    if (timeInMillis < System.currentTimeMillis()) {
                        this.add(Calendar.WEEK_OF_YEAR, 1)
                    }
                }

                Log.d("Notification", "Scheduling notification for ${calendar.time}")

                if (calendar.timeInMillis < System.currentTimeMillis()) {
                    Log.d("Notification", "Scheduled time is in the past. Adjusting to next week.")
                    calendar.add(Calendar.WEEK_OF_YEAR, 1)
                }

                val intent = Intent(context, NotificationReceiver::class.java).apply {
                    putExtra("medicineName", card.medicineName)
                    putExtra("notificationId", card.id)
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    card.id,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                // Налаштування повідомлення, що повторюється.
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7, // Повторення щотижня
                    pendingIntent
                )
            }
        }

        fun cancelNotification(context: Context, cardId: Int) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, NotificationReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                cardId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }
}
