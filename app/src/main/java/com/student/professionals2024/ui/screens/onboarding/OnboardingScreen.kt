package com.student.professionals2024.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.R
import com.student.professionals2024.domain.models.OnboardingScreens

@Composable
fun OnboardingScreen(currentOnboardInfoScreen: OnboardingScreens, onNextButtonPressed: () -> Unit, moveToLoginScreen: () -> Unit, screensLeft: Int) {
    val signUpButtonShow = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = currentOnboardInfoScreen.screenImage),
            contentDescription = "Main Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(340.dp)
                .testTag(currentOnboardInfoScreen.screenImage.toString())
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            currentOnboardInfoScreen.screenMainText,
            fontSize = 24.sp,
            modifier = Modifier.testTag("mainText"),
            style = TextStyle(
                color = colorResource(id = R.color.main_text_color),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            currentOnboardInfoScreen.screenSecondText,
            fontSize = 16.sp,
            style = TextStyle(
                color = colorResource(id = R.color.secondary_text_color),
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .width(340.dp)
                .height(240.dp)
        ) {
            if (!signUpButtonShow.value) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = moveToLoginScreen,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "Skip",
                            style = TextStyle(color = colorResource(id = R.color.main_text_color))
                        )
                    }
                    Button(
                        onClick = {
                            if(screensLeft == 1) signUpButtonShow.value = true
                            onNextButtonPressed()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = colorResource(
                                id = R.color.main_text_color
                            ), contentColor = Color.White
                        )
                    ) {
                        Text("Next")
                    }
                }
            } else {
                Button(
                    onClick = moveToLoginScreen,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = colorResource(
                            id = R.color.main_text_color
                        ), contentColor = Color.White
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp), shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Sign up")
                }
            }
        }
    }
}