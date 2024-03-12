package com.student.professionals2024.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.student.professionals2024.R
import com.student.professionals2024.domain.viewmodels.AuthViewModel
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

@Composable
fun AccountField(labelText: String, inputState: MutableState<String>, hint: String) {
    Column(modifier = Modifier.padding(top = 18.dp)) {
        LabelName(labelText, modifier = Modifier.padding(start = 24.dp))
        OutlinedTextField(value = inputState.value, onValueChange = {inputState.value = it}, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp), placeholder = { Text(hint, fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
    }
}

@Composable
fun PasswordField(textState: MutableState<String>, visibilityState: MutableState<Boolean>, labelName: String, hint: String) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        LabelName(labelName, modifier = Modifier.padding(start = 24.dp))
        OutlinedTextField(value = textState.value, onValueChange = {textState.value = it}, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
            visualTransformation = if (visibilityState.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {visibilityState.value = !visibilityState.value}) {
                    Icon(painter = painterResource(id = if (!visibilityState.value) R.drawable.password_visible else R.drawable.password_invisible), contentDescription = null)
                }
            },
            placeholder = { Text(hint, fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) }
        )
    }
}


@Composable
fun LabelName(text: String, modifier: Modifier = Modifier) {
    Text(text, fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin), modifier = modifier)
}

@Composable
fun TitleInfoOnLoginScreens(mainText: String, secondaryText: String) {
    Column {
        Text(
            mainText,
            fontSize = 24.sp,
            style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 40.dp, start = 24.dp)
        )
        Text(
            secondaryText,
            fontSize = 14.sp,
            style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin),
            modifier = Modifier.padding(start = 24.dp, top = 5.dp)
        )
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GoogleSignInHandler(viewModel: AuthViewModel, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Text("Or sign in using")
    Icon(
        painter = painterResource(id = R.drawable.google_icon),
        contentDescription = null,
        modifier = Modifier.clickable {
            val credentialManager = CredentialManager.create(context)


            val rawNonce = UUID.randomUUID().toString()
            val bytes = rawNonce.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }


            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("102387424725-hbc5va2vgbi861p6o43a6gd7sdslqaos.apps.googleusercontent.com")
                .setNonce(hashedNonce) // Provide the nonce if you have one
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            coroutineScope.launch {
                try {
                    val result = credentialManager.getCredential(
                        request = request,
                        context = context,
                    )

                    val googleIdTokenCredential = GoogleIdTokenCredential
                        .createFrom(result.credential.data)

                    val googleIdToken = googleIdTokenCredential.idToken

                    viewModel.loginWithGoogle(googleIdToken, hashedNonce)
                    navController.navigate("main")

                    // Handle successful sign-in
                } catch (e: Exception) {
                    Log.d("GOOGLE", e.message!!)
                }
            }
        })
}


