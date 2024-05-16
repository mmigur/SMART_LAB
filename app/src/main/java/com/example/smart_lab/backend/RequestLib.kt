package com.example.smart_lab.backend

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
fun userEmail(email: String): Boolean {
    val authRepository = createAuthRepository()
    val coroutineScope = rememberCoroutineScope()
    var isRegistered by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(email) {
        coroutineScope.launch {
            try {
                val response = authRepository.checkUser(email)
                isRegistered = response
            } catch (e: Exception) {
                isRegistered = false // Если произошла ошибка, считаем, что пользователь не зарегистрирован
            }
        }
    }
    return isRegistered ?: false
}