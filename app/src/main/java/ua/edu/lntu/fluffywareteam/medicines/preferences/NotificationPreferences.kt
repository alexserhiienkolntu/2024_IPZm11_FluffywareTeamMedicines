package ua.edu.lntu.fluffywareteam.medicines.preferences

import android.content.Context
import android.content.SharedPreferences

class NotificationPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FluffywareTeamMedicinesUserPreferences", Context.MODE_PRIVATE)

    fun setNotificationCheck(id: Int, state: Boolean) {
        sharedPreferences.edit().putBoolean("notification-$id", state).apply()
    }

    fun getNotificationCheck(id: Int): Boolean {
        return sharedPreferences.getBoolean("notification-$id", false)
    }
}