package com.example.smart_lab.auth

import android.content.Context
import android.content.SharedPreferences

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import com.example.smart_lab.navigation.Screen
import java.util.prefs.Preferences

@Composable
fun PinCodeScreen(
    navController: NavController
) {
    val pin = remember {
        mutableStateListOf<Int>(
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 40.dp, end = 24.dp)
                .clickable {
                   navController.navigate(Screen.MapUser.route)
                },
            text = "Пропустить",
            color = Color(0xFF57A9FF),
            fontSize = 20.sp,
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Создайте пароль",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Для защиты ваших персональных данных",
            color = Color.LightGray,
            fontSize = 17.sp
        )

        Spacer(modifier = Modifier.height(50.dp))


        InputDots(pin)

        Spacer(modifier = Modifier.height(60.dp))

        NumberBoard(
            onNumberClick = { mynumber ->
                when (mynumber) {
                    "" -> {}
                    "x" -> {
                        if (pin.isNotEmpty()) {
                            pin.removeLast()
                        } else{

                        }
                    }

                    else -> {
                        if (pin.size < 4)
                            pin.add(mynumber.toInt())
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (pin.size == 4) {
            if (pin.joinToString("") == "1234") {

            } else {
                Toast.makeText(
                    LocalContext.current, "Не успешно", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun InputDots(
    numbers: List<Int> = listOf(1, 2),
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        PinIndicator(filled = numbers.isNotEmpty())
        PinIndicator(filled = numbers.size > 1)
        PinIndicator(filled = numbers.size > 2)
        PinIndicator(filled = numbers.size > 3)
    }
}

@Composable
private fun PinIndicator(
    filled: Boolean,
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(16.dp)
            .clip(CircleShape)
            .background(if (filled) Color(0xFF1A6FEE) else Color.Transparent)
            .border(1.dp, Color(0xFF1A6FEE), CircleShape)
    )
}


@Composable
private fun NumberButton(
    modifier: Modifier = Modifier,
    number: String = "1",
    onClick: (number: String) -> Unit = {},
) {
    val isButtonPressed = remember { mutableStateOf(false) }
    val buttonColor = animateColorAsState(
        targetValue = if (isButtonPressed.value) Color(0xFF1A6FEE) else Color(0xFFF5F5F9),
        animationSpec = tween(durationMillis = 100)
    )
    val textColor = animateColorAsState(
        targetValue = if (isButtonPressed.value) Color.White else Color.Black,
        animationSpec = tween(durationMillis = 100)
    )

    Button(
        onClick = {
            isButtonPressed.value = true
            onClick(number)
            isButtonPressed.value = false
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor.value),
        modifier = Modifier
            .size(120.dp)
            .padding(10.dp)
    ) {
        Text(
            text = number,
            color = textColor.value,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NumberBoard(
    onNumberClick: (num: String) -> Unit,
) {
    val buttons = (1..9).toList()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.chunked(3).forEach { buttonRow ->
            Row(
            ) {
                buttonRow.forEach { buttonNumber ->

                    NumberButton(
                        number = buttonNumber.toString(),
                        onClick = onNumberClick,
                        modifier = Modifier
                    )
                }
            }
        }
        NumberBoardRow(listOf("", "0", "x"), onNumberClick = onNumberClick)
    }
}

@Composable
fun NumberBoardRow(
    num: List<String>,
    onNumberClick: (num: String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in num) {
            if(i == ""){
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .border(BorderStroke(1.dp, Color.White),
                            shape = CircleShape
                        )
                )
                continue
            }
            NumberButton(
                number = i,
                onClick = { onNumberClick(it) })
        }
    }
}