package com.example.smart_lab.backend.auth

import com.example.smart_lab.backend.auth.AuthResponse
import com.example.smart_lab.backend.auth.User
import retrofit2.http.Body
import retrofit2.http.POST

// Интерфейс для Retrofit
interface AuthApi {
    @POST("auth/check-user")
    suspend fun checkUser(@Body user: User): AuthResponse
}