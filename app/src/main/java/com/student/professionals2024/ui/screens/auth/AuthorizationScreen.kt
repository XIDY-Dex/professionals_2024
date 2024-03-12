package com.student.professionals2024.ui.screens.auth

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.student.professionals2024.R
import com.student.professionals2024.domain.viewmodels.AuthViewModel
import com.student.professionals2024.ui.components.AccountField
import com.student.professionals2024.ui.components.GoogleSignInHandler
import com.student.professionals2024.ui.components.PasswordField
import com.student.professionals2024.ui.components.TitleInfoOnLoginScreens


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AuthorizationScreen(onLogInButtonPressed: (String, String) -> Unit, onSignUpButtonPressed: () -> Unit, forgotPasswordClick: () -> Unit, viewModel: AuthViewModel, navController: NavController) {
    val emailInputState = remember { mutableStateOf("") }
    val passwordInputState = remember { mutableStateOf("") }
    val passwordVisible = remember {mutableStateOf(false)}
    val rememberPasswordState = remember { mutableStateOf(false )}
    val signUpButtonEnabled = remember {
        derivedStateOf { emailInputState.value.isNotEmpty() && passwordInputState.value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInputState.value).matches() }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp), horizontalAlignment = Alignment.Start) {
        TitleInfoOnLoginScreens(mainText = "Welcome back", secondaryText = "Fill in your email and password to continue")
        AccountField("Email", emailInputState, "*********@gmail.com")
        PasswordField(labelName = "Password", visibilityState = passwordVisible, hint = "********", textState = passwordInputState)
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(start = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = rememberPasswordState.value, onCheckedChange = {rememberPasswordState.value = it}, colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.main_text_color), checkmarkColor = Color.White))
            Text(text = "Remember password", fontSize = 12.sp)
            Text(text = "Forgot password?", modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp)
                .clickable { forgotPasswordClick() }
                , textAlign = TextAlign.End, fontSize = 12.sp, style = TextStyle(color = colorResource(id = R.color.main_text_color)))
        }
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 190.dp), contentAlignment = Alignment.BottomCenter) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = { onLogInButtonPressed(emailInputState.value, passwordInputState.value) }, enabled = signUpButtonEnabled.value, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 30.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = colorResource(id = R.color.main_text_color),
                        disabledContainerColor = colorResource(id = R.color.disabled_button_color),
                        disabledContentColor = Color.White
                    )) {
                    Text(text = "Sign in")
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
                    Text("Don't have an account? ")
                    Text("Sign up", style = TextStyle(color  = colorResource(id = R.color.main_text_color)), modifier = Modifier.clickable { onSignUpButtonPressed() })
                }

                GoogleSignInHandler(viewModel = viewModel, navController = navController)
            }
        }
    }
}