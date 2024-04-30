package com.example.smart_lab.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.smart_lab.R
import com.example.smart_lab.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true){
        delay(3000L)
        navController.navigate(route = Screen.Home.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan),
        contentAlignment = Alignment.Center,
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.splash_background_image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null
        )
    }
}