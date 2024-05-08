package com.example.smart_lab.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_lab.R
import com.example.smart_lab.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController){
    var enterUserOrEmailText by remember { mutableStateOf(TextFieldValue("")) }
    var isEmailValid by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp),
                painter = painterResource(id = R.drawable.hello_emoj_home_screen),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Добро пожаловать!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Войдите, чтобы пользоваться функциями\nприложения",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp),
            text = "Вход по E-mail",
            fontSize = 15.sp,
            color = Color.LightGray,
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .width(335.dp)
                .padding(start = 24.dp, end = 24.dp),
            value = enterUserOrEmailText,
            onValueChange = { newEmail ->
                enterUserOrEmailText = newEmail
                isEmailValid = validateEmail(email = newEmail)
            },
            placeholder = {
                Text(
                    text = "example@mail.ru",
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
                .height(55.dp)
                .width(335.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF1A6FEE)),
            onClick = {
                navController.navigate(route = Screen.EmailCodeScreen.route)
            },
            // true
            enabled = isEmailValid,
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = "Далее",
                color = Color.White,
                fontSize = 17.sp
            )
        }
        Spacer(modifier = Modifier.height(300.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Или войдите с помощью",
            fontSize = 15.sp,
            color = Color.LightGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
                .height(55.dp)
                .width(335.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            Text(
                text = "Войти с Яндекс",
                color = Color.Black,
                fontSize = 17.sp
            )
        }
    }
}

fun validateEmail(email: TextFieldValue): Boolean {
    val emailRegex = "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+".toRegex()
    return emailRegex.matches(email.text)
}
