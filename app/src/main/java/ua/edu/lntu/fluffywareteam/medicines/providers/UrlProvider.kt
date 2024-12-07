package ua.edu.lntu.fluffywareteam.medicines.providers


class UrlProvider {
    companion object {
        private const val BASE_URL: String = "https://fluffywareteam.pythonanywhere.com/medicines/api/"

        fun getRegisterUrl(): String {
            return BASE_URL + "auth/register/"
        }

        fun getLoginUrl(): String {
            return BASE_URL + "auth/login/"
        }

        fun getMedicinesUrl(): String {
            return BASE_URL + "medicines/"
        }

        fun getNotificationCardsUrl(): String {
            return BASE_URL + "notification-cards/"
        }
    }
}
