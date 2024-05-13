package com.example.smart_lab.backend

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

fun isUserRegistered(email: String): Boolean {
    val url = "https://b95d-2a03-d000-6583-1ba0-48f1-c600-58b8-2a33.ngrok-free.app/auth/check-user"
    val client = OkHttpClient()

    val requestBody = """{"email": "$email"}""".trimIndent()
    val request = Request.Builder()
        .url("$url?$requestBody")
        .build()

    return try {
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        if (responseBody != null) {
            val jsonObject = JSONObject(responseBody)
            val isRegistered = jsonObject.getBoolean("isRegistered")
            isRegistered
        } else {
            false
        }
    } catch (e: Exception) {
        false
    }
}