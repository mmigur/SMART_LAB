package com.example.smart_lab.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_lab.storage.Screen
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapUserScreen(
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var genderDate by remember { mutableStateOf("") }

    val buttonColor = if (
        areAllFieldsFilled(
            firstName,
            middleName,
            lastName,
            birthDate,
            genderDate
        )) {
        Color(0xFF1A6FEE)
    } else {
        Color(0xFFC9D4FB)
    }

    val buttonEnabled = areAllFieldsFilled(
        firstName,
        middleName,
        lastName,
        birthDate,
        genderDate
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier,
                text = "Создание карты\nпациента",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(85.dp))

            Text(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.MapUser.route)
                    },
                text = "Пропустить",
                color = Color(0xFF57A9FF),
                fontSize = 20.sp,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp),
            text = "Без карты пациента вы не сможете заказать анализы.",
            color = Color.LightGray,
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 24.dp),
            text = "В картах пациентов будут храниться результаты анализов вас и ваших близких.",
            color = Color.LightGray,
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            placeholder = {
                Text(
                    text = "Имя",
                    color = Color.LightGray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .width(335.dp)
                .padding(start = 24.dp, end = 24.dp),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = middleName,
            onValueChange = { middleName = it },
            placeholder = {
                Text(
                    text = "Отчество",
                    color = Color.LightGray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .width(335.dp)
                .padding(start = 24.dp, end = 24.dp),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            placeholder = {
                Text(
                    text = "Фамилия",
                    color = Color.LightGray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .width(335.dp)
                .padding(start = 24.dp, end = 24.dp),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            placeholder = {
                Text(
                    text = "Дата рождения",
                    color = Color.LightGray
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .width(335.dp)
                .padding(start = 24.dp, end = 24.dp),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = genderDate,
            onValueChange = { genderDate = it },
            placeholder = {
                Text(
                    text = "Пол",
                    color = Color.LightGray
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .width(335.dp)
                .padding(start = 24.dp, end = 24.dp),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* Действие при нажатии на кнопку */ },
            enabled = buttonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
                .height(55.dp)
                .width(335.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Отправить",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

private fun areAllFieldsFilled(
    firstName: String,
    middleName: String,
    lastName: String,
    birthDate: String,
    genderDate: String
): Boolean {
    return  firstName.isNotEmpty() &&
            middleName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            birthDate.isNotEmpty() &&
            genderDate.isNotEmpty()
}