package com.student.professionals2024.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.student.professionals2024.R
import com.student.professionals2024.core.UserState
import com.student.professionals2024.domain.viewmodels.SendPackageViewModel

@Composable
fun TransactionScreen(viewModel: SendPackageViewModel, navController: NavController) {
    val transactionState = viewModel.orderTransactionState.collectAsState()
    val trackNumber = viewModel.packageInfo.value?.trackNumber ?: ""
    LaunchedEffect(key1 = true) {
        viewModel.makeTransaction()
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 24.dp, end = 24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if(transactionState.value == UserState.Loading || transactionState.value == UserState.Default) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                strokeWidth = 6.dp,
                color = colorResource(id = R.color.loading_component_color)
            )
        }
        else {
            Image(painter = painterResource(id = R.drawable.transaction_success), contentDescription = null, modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally), contentScale = ContentScale.Fit)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text("Transaction status", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Text("Your rider is on the way to your destination", fontSize = 14.sp)
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text("Tracking number: ")
            Text(trackNumber, color = colorResource(id = R.color.main_text_color))
        }
        Spacer(modifier = Modifier.height(120.dp))
        OutlinedButton(onClick = {
            navController.navigate("track") {
                popUpTo("package") {
                    inclusive = true
                }
            }

        }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_text_color), contentColor = Color.White), border = BorderStroke(1.dp, colorResource(id = R.color.main_text_color)), shape = RoundedCornerShape(5.dp), modifier = Modifier.fillMaxWidth()) {
            Text("Track my item")
        }
        OutlinedButton(onClick = {
            navController.navigate("home") {
                popUpTo("package") {
                    inclusive = true
                }
            }
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = colorResource(id = R.color.main_text_color)), border = BorderStroke(1.dp, colorResource(id = R.color.main_text_color)), shape = RoundedCornerShape(5.dp), modifier = Modifier.fillMaxWidth()) {
            Text("Go back to homepage")
        }
    }
}
