package com.example.smart_lab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smart_lab.on_board.OnBoarding
import com.example.smart_lab.on_board.PagerScreen
import com.example.smart_lab.screens.HomeScreen
import com.example.smart_lab.screens.SplashScreen

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

        composable(
            route = Screen.OnBoard.route
        ){
            OnBoarding(navController = navController)
        }
    }
}