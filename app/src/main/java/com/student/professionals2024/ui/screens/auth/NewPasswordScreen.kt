package com.student.professionals2024.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.student.professionals2024.R
import com.student.professionals2024.ui.components.PasswordField
import com.student.professionals2024.ui.components.TitleInfoOnLoginScreens

@Composable
fun NewPasswordScreen(onLogInButtonPressed: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp)) {
        val passwordInputState = remember { mutableStateOf("") }
        val passwordVisibility = remember { mutableStateOf(false) }
        val passwordRepeatInputState = remember { mutableStateOf("") }
        val passwordRepeatVisibility = remember { mutableStateOf(false) }
        val loginButtonEnabled = remember { derivedStateOf { passwordInputState.value.isNotEmpty() && passwordRepeatInputState.value.isNotEmpty() && passwordInputState.value == passwordRepeatInputState.value} }
        TitleInfoOnLoginScreens(mainText = "New password", secondaryText = "Enter new password")
        Spacer(modifier = Modifier.padding(top = 40.dp))
        PasswordField(textState = passwordInputState, visibilityState = passwordVisibility, labelName = "Password", hint = "*********")
        PasswordField(textState = passwordRepeatInputState, visibilityState = passwordRepeatVisibility, labelName = "Confirm password", hint = "*********")
        Button(onClick = { }, enabled = loginButtonEnabled.value, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.main_text_color),
                disabledContainerColor = colorResource(id = R.color.disabled_button_color),
                disabledContentColor = Color.White
            )) {
            Text(text = "Log in")
        }
    }
}