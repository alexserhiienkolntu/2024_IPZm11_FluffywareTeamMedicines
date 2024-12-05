package ua.edu.lntu.fluffywareteam.medicines.services

import ua.edu.lntu.fluffywareteam.medicines.preferences.UserPreferences
import ua.edu.lntu.fluffywareteam.medicines.providers.UrlProvider
import ua.edu.lntu.fluffywareteam.medicines.requests.RequestPerformer
import ua.edu.lntu.fluffywareteam.medicines.utils.JSONUtils

class AuthService {
    companion object {
        fun register(username: String, password: String, callback: (String) -> Unit) {
            val json = JSONUtils.createJSONAuth(username, password)

            RequestPerformer.post(UrlProvider.getRegisterUrl(), json = json) { response ->
                if (response != null) {
                    val message = JSONUtils.getValueFromResponse(response, "message")
                    callback(message)
                } else {
                    callback("Такий користувач вже існує!")
                }
            }
        }

        fun login(username: String, password: String, userPreferences: UserPreferences , callback: (String) -> Unit) {
            val json = JSONUtils.createJSONAuth(username, password)

            RequestPerformer.post(UrlProvider.getLoginUrl(), json = json) { response ->
                if (response != null) {
                    val name = JSONUtils.getValueFromResponse(response, "username")
                    val token = JSONUtils.getValueFromResponse(response, "access_token")
                    userPreferences.setUsername(name)
                    userPreferences.setAccessToken(token)
                    callback("Добрий день, $name!")
                } else {
                    callback("Невірні облікові дані!")
                }
            }
        }
    }
}