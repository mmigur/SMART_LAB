package com.example.smart_lab

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object Home: Screen(route = "home_screen")
}