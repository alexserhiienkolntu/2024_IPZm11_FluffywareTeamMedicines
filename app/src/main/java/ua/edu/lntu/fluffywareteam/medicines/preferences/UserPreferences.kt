package ua.edu.lntu.fluffywareteam.medicines.preferences

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FluffywareTeamMedicinesUserPreferences", Context.MODE_PRIVATE)

    fun setUsername(username: String) {
        sharedPreferences.edit().putString("username", username).apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun setAccessToken(token: String) {
        sharedPreferences.edit().putString("access-token", token).apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access-token", null)
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}