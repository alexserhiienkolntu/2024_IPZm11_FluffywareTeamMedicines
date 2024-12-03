package ua.edu.lntu.fluffywareteam.medicines.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.edu.lntu.fluffywareteam.medicines.R

class NotificationReceiver : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("NotificationReceiver", "Received broadcast")

        val medicineName = intent?.getStringExtra("medicineName") ?: "???"
        val notificationId = intent?.getIntExtra("notificationId", 0) ?: 0

        val builder = NotificationCompat.Builder(context!!, "medicine_channel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Нагадування про прийом ліків!")
            .setContentText("Час прийняти $medicineName!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId, builder.build())
    }
}
