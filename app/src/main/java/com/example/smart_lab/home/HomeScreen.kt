package com.example.smart_lab.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Card
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.models.AnalyzeCardModel
import com.example.models.FilterButtonModel
import com.example.models.NewsCardModel
import com.example.models.filterButtonTextList
import com.example.smart_lab.R
import com.example.smart_lab.navigation.SetupNavGraph
import kotlin.math.ln

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
){
    val newsList = listOf(
        NewsCardModel(
            title = "Чек - ап для \nмужчин",
            description = "9 исследований",
            price = 8000,
            image = R.drawable.man_image_card
        ),
        NewsCardModel(
            title = "Чек - ап для \nмужчин",
            description = "9 исследований",
            price = 8000,
            image = R.drawable.man_image_card
        )
    )
    val (selectedFilter, setSelectedFilter) = remember { mutableStateOf<FilterButtonModel?>(null) }
    val analyzesList = listOf(
        AnalyzeCardModel(
            title = "ПЦР тест на выявление РНК короновируса стандартный",
            dateOfComplite = "1 день",
            price = 1800
        ),
        AnalyzeCardModel(
            title = "ПЦР тест на выявление РНК короновируса стандартный",
            dateOfComplite = "1 день",
            price = 1800
        ),
        AnalyzeCardModel(
            title = "ПЦР тест на выявление РНК короновируса стандартный",
            dateOfComplite = "1 день",
            price = 1800
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, start = 24.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = "Акции и новости",
                fontSize = 17.sp,
                color = Color(0xFF939396),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(items = newsList) { item ->
                NewsCard(
                    title = item.title,
                    description = item.description,
                    price = item.price,
                    image = item.image
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(items = filterButtonTextList) { item ->
                FilterChip(
                    selected = selectedFilter == item,
                    onClick = {
                        setSelectedFilter(if (selectedFilter == item) null else item)
                        item.enabled.value = !item.enabled.value
                    },
                    content = {
                        Text(
                            text = item.text,
                            color = if (selectedFilter == item) Color.White else Color(0xFF7E7E9A)
                        )
                    },
                    modifier = Modifier.height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ChipDefaults.filterChipColors(Color(0xFF1A6FEE), Color(0xFF1A6FEE))
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier.padding(start = 20.dp)
        ){
            Text(
                text = "Каталог анализов",
                fontSize = 17.sp,
                color = Color(0xFF939396),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(items = analyzesList) { item ->
                AnalyzeCard(
                    title = item.title,
                    dateOfComplite = item.dateOfComplite,
                    price = item.price
                )
            }
        }
    }
}

@Composable
fun AnalyzeCard(
    title: String,
    dateOfComplite: String,
    price: Int,
){
    Card(
        modifier = Modifier
            .height(136.dp)
            .width(400.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row (
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ){
            Column(
                modifier = Modifier
                    .width(250.dp),
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = dateOfComplite,
                    fontSize = 14.sp,
                    color = Color(0xFF939396)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = price.toString() + " Р",
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    color = Color.Black
                )
            }
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.Bottom),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Добавить",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun NewsCard(
   title: String,
   description: String,
   price: Int,
   image: Int
){
    Card (
        modifier = Modifier
            .height(152.dp)
            .width(270.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color(0xFFA2CBFF)
    ){
        Row {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = price.toString() + " Р",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            Image(
                modifier = Modifier
                    .height(152.dp)
                    .width(145.dp)
                    .scale(1.2f),
                painter = painterResource(id = image),
                contentDescription = null
            )
        }
    }
}
