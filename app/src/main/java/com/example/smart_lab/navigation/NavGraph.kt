package com.example.smart_lab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smart_lab.screens.OnBoarding
import com.example.smart_lab.auth.EmailCodeScreen
import com.example.smart_lab.home.HomeScreen
import com.example.smart_lab.auth.MapUserScreen
import com.example.smart_lab.auth.PinCodeScreen
import com.example.smart_lab.auth.SignInScreen
import com.example.smart_lab.screens.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.MapUser.route
    ){
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

        composable(
            route = Screen.SignIn.route
        ){
            SignInScreen(navController = navController)
        }

        composable(
            route = Screen.EmailCodeScreen.route
        ){
            EmailCodeScreen(navController = navController)
        }

        composable(
            route = Screen.PinCode.route
        ){
            PinCodeScreen(navController = navController)
        }

        composable(
            route = Screen.MapUser.route
        ){
            MapUserScreen(navController = navController)
        }

        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
    }
}