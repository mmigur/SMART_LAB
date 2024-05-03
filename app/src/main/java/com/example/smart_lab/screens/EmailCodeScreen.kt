package com.example.smart_lab.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
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
import com.example.smart_lab.storage.Screen
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
    ), mutableStateOf(
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
fun EmailCodeScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    var isButtonEnabled by remember { mutableStateOf(true) }
    var countdown by remember { mutableStateOf(60) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        FilledIconButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 24.dp),
            onClick = {
                navController.navigate(route = Screen.SignIn.route)
            },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color(0xFFE2E2E2)),
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
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            for (i in textList.indices) {
                InputView(
                    value = textList[i].value,
                    onValueChange = { newValue ->
                        if (textList[i].value.text != "") {
                            if (newValue.text == "") {
                                textList[i].value = TextFieldValue(
                                    text = "",
                                    selection = TextRange.Zero
                                )
                            }
                            return@InputView
                        }

                        textList[i].value = TextFieldValue(
                            text = newValue.text,
                            selection = TextRange(newValue.text.length)
                        )

                        connectInputtedCode(textList) {
                            focusManager.clearFocus()
                            keyBoardController?.hide()

                            if (it) {
                                Toast.makeText(
                                    context,
                                    "Успешно",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {
                                Toast.makeText(
                                    context,
                                    "Ошибка",
                                    Toast.LENGTH_SHORT
                                ).show()

                                for (text in textList) {
                                    text.value = TextFieldValue(
                                        text = "",
                                        selection = TextRange.Zero
                                    )
                                }
                            }
                        }

                        nextFocus(
                            textList = textList,
                            requesterList = requesterList,
                        )
                    },
                    focusRequester = requesterList[i]
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Отправить код повторно можно будет через $countdown секунд",
            color = Color(0xFF939396)
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
                        navController.navigate(Screen.PinCode.route)
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

public fun connectInputtedCode(
    textList: List<MutableState<TextFieldValue>>,
    onVerifyCode: ((success: Boolean) -> Unit)? = null
) {
    fun verifyCode(
        code: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (code == CODE) {
            onSuccess()
        } else {
            onError()
        }
    }

    var code = ""
    for (text in textList) {
        code += text.value.text
    }
    if (code.length == 4) {
        verifyCode(code, onSuccess = {
            onVerifyCode?.let {
                it(true)
            }
        }, onError = {
            onVerifyCode?.let {
                it(false)
            }
        })
    }
}

private fun nextFocus(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>
) {
    for (index in textList.indices) {
        if (textList[index].value.text == "") {
            if (index < textList.size) {
                requesterList[index].requestFocus()
                break
            }
        }
    }
}

@Composable
fun InputView(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    focusRequester: FocusRequester
) {
    BasicTextField(
        readOnly = false,
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE2E2E2))
            .wrapContentSize()
            .focusRequester(focusRequester),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        },
        cursorBrush = SolidColor(Color.Gray),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(onDone = null)
    )
}