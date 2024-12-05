package ua.edu.lntu.fluffywareteam.medicines.utils

import org.json.JSONObject

class JSONUtils {
    companion object {
        fun createJSONAuth(username: String, password: String): String {
            return JSONObject().apply {
                put("username", username)
                put("password", password)
            }.toString()
        }

        fun getValueFromResponse(response: String, key: String): String {
            try {
                val jsonResponse = JSONObject(response)
                val message = jsonResponse.getString(key)
                return message
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return "Cannot parse response!"
        }
    }
}