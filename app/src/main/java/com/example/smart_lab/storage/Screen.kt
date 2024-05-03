package com.example.smart_lab.storage

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object SignIn: Screen(route = "signIn_screen")
    object OnBoard: Screen(route = "on_board_screen")
    object EmailCodeScreen: Screen(route = "email_code_screen")
    object PinCode: Screen(route = "pin_code_screen")
}