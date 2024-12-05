package ua.edu.lntu.fluffywareteam.medicines.requests

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class RequestPerformer {
    companion object {
        fun get(url: String, callback: (String?) -> Unit) {
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    CoroutineScope(Dispatchers.Main).launch {
                        callback(null)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        CoroutineScope(Dispatchers.Main).launch {
                            callback(responseBody)
                        }
                    } else {
                        CoroutineScope(Dispatchers.Main).launch {
                            callback(null)
                        }
                    }
                }
            })
        }

        fun post(url: String, json: String, callback: (String?) -> Unit) {
            val client = OkHttpClient()

            val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    CoroutineScope(Dispatchers.Main).launch {
                        callback(null)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        CoroutineScope(Dispatchers.Main).launch {
                            callback(responseBody)
                        }
                    } else {
                        CoroutineScope(Dispatchers.Main).launch {
                            callback(null)
                        }
                    }
                }
            })
        }

    }
}