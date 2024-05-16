package com.example.smart_lab.backend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Интерфейс для Retrofit
interface AuthApi {
    @POST("auth/check-user")
    suspend fun checkUser(@Body user: User): AuthResponse
}

// Класс для запроса
data class User(val email: String)

// Класс для ответа
data class AuthResponse(val errorMessage: String, val isRegistered: Boolean)

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