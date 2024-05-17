package com.example.smart_lab.backend.auth

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Класс для выполнения запроса
class AuthRepository(private val authApi: AuthApi) {
    suspend fun checkUser(email: String): Boolean {
        val response = authApi.checkUser(User(email))
        return response.isRegistered
    }
}

// Функция для инициализации Retrofit и создания AuthRepository
fun createAuthRepository(): AuthRepository {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://b683-2a03-d000-6583-1ba0-48f1-c600-58b8-2a33.ngrok-free.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authApi = retrofit.create(AuthApi::class.java)
    return AuthRepository(authApi)
}