package com.student.professionals2024.ui.screens.auth

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.student.professionals2024.R
import com.student.professionals2024.ui.components.TitleInfoOnLoginScreens
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPVerificationScreen(otpCorrect: Boolean? = null, onSendButtonPressed: (String) -> Unit) {
    val codeList = remember { mutableStateListOf("", "", "", "", "", "") }
    val secondsLeftToResend = remember { mutableIntStateOf(60) }
    val userCanResendCode = remember { derivedStateOf { secondsLeftToResend.intValue == 0 } }
    val buttonEnabled = remember { derivedStateOf { codeList.all{ it.isNotEmpty() } }}

    LaunchedEffect(key1 = userCanResendCode) {
        while(secondsLeftToResend.intValue > 0) {
            delay(1000L)
            secondsLeftToResend.intValue -= 1
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(top = 30.dp)) {
        TitleInfoOnLoginScreens(mainText = "OTP Verification", secondaryText = "Enter the 6 digits that were sent to your email")
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            for(index in codeList.indices) {
                OutlinedTextField(
                    value = codeList[index],
                    onValueChange = {
                        if(it.length <= 1) {
                            codeList[index] = it
                        }
                    },
                    maxLines = 1,
                    singleLine = true,
                    modifier = Modifier
                        .width(42.dp)
                        .height(42.dp)
                        .padding(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor =  if(otpCorrect == true) Color.White else Color.Red, focusedContainerColor = if(otpCorrect == true) Color.White else Color.Red, focusedTextColor = Color.Black, unfocusedTextColor = Color.Black)
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
            if(userCanResendCode.value) {
                Text("If you didn't received code, ")
                Text("resend", modifier = Modifier.clickable {}, style = TextStyle(color = colorResource(
                    id = R.color.main_text_color
                )))
            }
            else {
                Text("You will be able to resend code in ${secondsLeftToResend.value} seconds")
            }
        }
        Button(onClick = { onSendButtonPressed(codeList.toList().toString()) }, enabled = buttonEnabled.value, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.main_text_color),
                disabledContainerColor = colorResource(id = R.color.disabled_button_color),
                disabledContentColor = Color.White
            )) {
            Text(text = "Send OTP")
        }
    }
}
