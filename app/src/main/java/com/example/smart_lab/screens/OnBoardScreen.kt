package com.example.smart_lab.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_lab.R
import com.example.smart_lab.storage.OnBoardingPage
import com.example.smart_lab.storage.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding(navController: NavController){
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagesState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0F
    ) { 3 }

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        HorizontalPager(
            state = pagesState,
            verticalAlignment = Alignment.Bottom
        ) {position ->
            PagerScreen(
                OnBoardingPage = pages[position],
                Selected = pagesState.currentPage,
                navController
            )
        }
    }
}

@Composable
fun CustomIndicator(isSelected: Boolean){
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(
                color = if(isSelected) Color(0xFF57A9FF) else Color.LightGray,
                shape = CircleShape).size(15.dp),
    )
}

@Composable
fun PagerScreen(
    OnBoardingPage: OnBoardingPage,
    Selected:Int,
    navController: NavController
){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val hasShownFirstRunScreen = remember {
        sharedPreferences.getBoolean("hasShownFirstRunScreen", false)
    }

    if (!hasShownFirstRunScreen){
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        top = 24.dp
                    )
            ){
                Text(
                    modifier = Modifier.clickable {
                        navController.navigate(route = Screen.SignIn.route)
                    },
                    text = OnBoardingPage.buttonText,
                    color = Color(0xFF57A9FF),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(50.dp))
                Image(
                    modifier = Modifier
                        .height(220.dp)
                        .width(220.dp),
                    painter = painterResource(id = R.drawable.on_board_plus_image),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = OnBoardingPage.title,
                    color = Color(0xFF00B712),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = OnBoardingPage.titleDescription,
                    color = Color.Gray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(62.dp))
            Row (
                modifier =  Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                repeat(times = 3){
                    CustomIndicator(isSelected = Selected == it)
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .padding(bottom = 24.dp),
                painter = painterResource(id = OnBoardingPage.image),
                contentDescription = null
            )
        }
    }
    else {
        navController.navigate(Screen.EmailCodeScreen.route)
    }
}