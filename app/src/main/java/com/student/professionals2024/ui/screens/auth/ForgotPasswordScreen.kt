package com.student.professionals2024.ui.screens.auth

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.student.professionals2024.R
import com.student.professionals2024.ui.components.AccountField
import com.student.professionals2024.ui.components.TitleInfoOnLoginScreens

@Composable
fun ForgotPasswordScreen(onSendCodeButtonPressed: (String) -> Unit) {
    val emailInputState = remember { mutableStateOf("") }
    val signUpButtonEnabled = remember {
        derivedStateOf {
            emailInputState.value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInputState.value).matches()
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
        TitleInfoOnLoginScreens(mainText = "Forgot password", secondaryText = "Enter your email address")
        Spacer(modifier = Modifier.height(60.dp))
        AccountField(labelText = "Email", inputState = emailInputState, hint = "**********@gmail.com")
        Button(onClick = { onSendCodeButtonPressed(emailInputState.value) }, enabled = signUpButtonEnabled.value, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = colorResource(id = R.color.main_text_color),
            disabledContainerColor = colorResource(id = R.color.disabled_button_color),
            disabledContentColor = Color.White
        )) { Text(text = "Send OTP") }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Remember password? ")
            Text("Sign in", style = TextStyle(color = colorResource(id = R.color.main_text_color)))
        }
    }

}