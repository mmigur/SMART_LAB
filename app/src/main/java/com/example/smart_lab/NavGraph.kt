package com.example.smart_lab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smart_lab.common.HomeScreen
import com.example.smart_lab.common.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        
        composable(
            route = Screen.Splash.route
        ){
            SplashScreen(navController = navController)
        }
    }
}