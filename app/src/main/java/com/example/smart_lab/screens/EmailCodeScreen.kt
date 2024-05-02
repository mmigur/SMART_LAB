package com.example.smart_lab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smart_lab.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val CODE = "1234"
private val textList = listOf(
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange.Zero
        )
    )
)

private val requesterList = listOf(
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
    FocusRequester()
)
@Composable
fun EmailCodeScreen(navController: NavController){
    val focusManager = LocalFocusManager.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    var isButtonEnabled by remember { mutableStateOf(true) }
    var countdown by remember { mutableStateOf(60) }
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ){
        FilledIconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp),
            onClick = {
                navController.navigateUp()
            },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.LightGray),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(130.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Введите код из E-mail",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            for (i in textList.indices){
                InputView(
                    value = textList[i].value,
                    onValueChange = {newValue ->

                    },
                    focusRequester = requesterList[i]
                )
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Отправить код повторно можно будет через $countdown секунд",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
                .height(40.dp)
                .width(240.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                if (isButtonEnabled) {
                    isButtonEnabled = false
                    coroutineScope.launch {
                        while (countdown > 0) {
                            delay(1000)
                            countdown--
                        }
                        isButtonEnabled = true
                        countdown = 60
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF1A6FEE)),
            enabled = isButtonEnabled,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Отправить код")
        }
    }
    LaunchedEffect(key1 = null, block = {
        delay(300)
        requesterList[0].requestFocus()
    })
}

@Composable
fun InputView(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    focusRequester: FocusRequester
){
    BasicTextField(
        readOnly = false,
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
            .wrapContentSize()
            .focusRequester(focusRequester),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ){
                innerTextField()
            }
        },
        cursorBrush = SolidColor(Color.White),
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(onDone = null)
    )
}