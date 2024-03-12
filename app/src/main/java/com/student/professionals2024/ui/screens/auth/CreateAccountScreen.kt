package com.student.professionals2024.ui.screens.auth

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.student.professionals2024.R
import com.student.professionals2024.domain.viewmodels.AuthViewModel
import com.student.professionals2024.ui.components.AccountField
import com.student.professionals2024.ui.components.GoogleSignInHandler
import com.student.professionals2024.ui.components.PasswordField
import com.student.professionals2024.ui.components.TitleInfoOnLoginScreens


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CreateAccountScreen(onSignUpButtonPressed: (String, String, String, String) -> Unit, returnToLoginScreen: () -> Unit, viewModel: AuthViewModel, navController: NavController) {
    val fullNameInputState = remember { mutableStateOf("") }
    val phoneNumberInputState = remember { mutableStateOf("") }
    val emailAddressInputState = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val passwordInputState = remember { mutableStateOf("") }
    val passwordRepeatState = remember { mutableStateOf("") }
    val passwordRepeatVisibility = remember { mutableStateOf(false) }
    val checkBoxState = remember { mutableStateOf(false) }
    val signUpButtonEnabled = remember {
        derivedStateOf {
            fullNameInputState.value.isNotEmpty() && phoneNumberInputState.value.isNotEmpty()
                    && emailAddressInputState.value.isNotEmpty() && passwordInputState.value.isNotEmpty()
                    && passwordRepeatState.value.isNotEmpty() && passwordInputState.value == passwordRepeatState.value
                    && checkBoxState.value && Patterns.EMAIL_ADDRESS.matcher(emailAddressInputState.value).matches()
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TitleInfoOnLoginScreens(
            mainText = "Create account",
            secondaryText = "Complete the sign up process to get started"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp), horizontalAlignment = Alignment.Start
        ) {
            AccountField(
                labelText = "Full name",
                inputState = fullNameInputState,
                hint = "Ivanov Ivan"
            )
            AccountField(
                labelText = "Phone number",
                inputState = phoneNumberInputState,
                hint = "+7(999)999-99-99"
            )
            AccountField(
                labelText = "Email address",
                inputState = emailAddressInputState,
                hint = "**********@gmail.com"
            )
            PasswordField(
                textState = passwordInputState,
                visibilityState = passwordVisibility,
                labelName = "Password",
                hint = "********"
            )
            PasswordField(
                textState = passwordRepeatState,
                visibilityState = passwordRepeatVisibility,
                labelName = "Confirm password",
                hint = "********"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 20.dp)
            ) {
                Checkbox(
                    checked = checkBoxState.value,
                    onCheckedChange = { checkBoxState.value = it },
                    colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.main_text_color))
                )
                Text(text = "By ticking this box, you agree to our Terms and conditions and private policy")
            }
            Button(
                onClick = {
                    onSignUpButtonPressed(
                        emailAddressInputState.value,
                        passwordInputState.value,
                        phoneNumberInputState.value,
                        fullNameInputState.value
                    )
                }, enabled = signUpButtonEnabled.value, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 30.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = colorResource(id = R.color.main_text_color),
                    disabledContainerColor = colorResource(id = R.color.disabled_button_color),
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Sign up")
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text("Already have an account? ")
                    Text("Sign in", modifier = Modifier.clickable { returnToLoginScreen() }, style = TextStyle(color = MaterialTheme.colorScheme.primary))
                }
                GoogleSignInHandler(viewModel = viewModel, navController = navController)
            }
        }
    }
}