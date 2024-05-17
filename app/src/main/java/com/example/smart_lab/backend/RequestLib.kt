package com.example.smart_lab.backend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.smart_lab.backend.auth.createAuthRepository
import kotlinx.coroutines.launch


suspend fun userEmail(email: String): Boolean {
    val authRepository = createAuthRepository()
    try {
        val response = authRepository.checkUser(email)
        return response
    } catch (e: Exception) {
        return false // Если произошла ошибка, считаем, что пользователь не зарегистрирован
    }
}