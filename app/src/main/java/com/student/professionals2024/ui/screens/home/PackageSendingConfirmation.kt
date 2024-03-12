package com.student.professionals2024.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.student.professionals2024.R
import com.student.professionals2024.domain.viewmodels.SendPackageViewModel
import com.student.professionals2024.ui.components.PackageInfo


@Composable
fun PackageSendingConfirmation(viewModel: SendPackageViewModel, navController: NavController, paddingValues: PaddingValues) {
    val packageInfo = viewModel.packageInfo.collectAsState()
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .height(100.dp)) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.go_back_icon),
                contentDescription = null,
                tint = colorResource(id = R.color.main_text_color),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
        }
        Text(
            "Send a package",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp),
            fontSize = 16.sp,
            color = Color.LightGray
        )
        HorizontalDivider(thickness = 3.dp, color = Color.LightGray)

        Box(contentAlignment = Alignment.TopStart, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 15.dp, bottom = paddingValues.calculateBottomPadding())) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text("Package Information", fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.main_text_color), fontSize = 16.sp)
                Text("Origin details", modifier = Modifier.padding(top = 5.dp), fontSize = 12.sp)
                Text(packageInfo.value?.originAddress ?: "", fontSize = 12.sp, color = Color.LightGray)
                Text(packageInfo.value?.originPhoneNumber ?: "", fontSize = 12.sp, color = Color.LightGray)
                Text("Destination details", modifier = Modifier.padding(top = 5.dp), fontSize = 12.sp)
                packageInfo.value?.destinations!!.forEachIndexed { index, destination ->
                    Text("${index}. ${destination.address}", fontSize = 12.sp, color = Color.LightGray)
                    Text(destination.phoneNumber, fontSize = 12.sp, color = Color.LightGray)
                }
                Text("Package details", modifier = Modifier.padding(top = 5.dp), fontSize = 12.sp)
                PackageInfo(mainText = "Package items", secondaryText = packageInfo.value?.itemsDescription ?: "")
                PackageInfo(mainText = "Weight of items", secondaryText = packageInfo.value?.itemsWeight ?: "")
                PackageInfo(mainText = "Worth of Items", secondaryText = packageInfo.value?.itemsWorth ?: "")
                PackageInfo(mainText = "Tracking number", secondaryText = packageInfo.value?.trackNumber ?: "")
                HorizontalDivider(thickness = 3.dp, color = Color.LightGray, modifier = Modifier.padding(top = 40.dp))
                Box(contentAlignment = Alignment.TopStart, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)) {
                    Text("Charges", fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.main_text_color), fontSize = 16.sp)
                }
                PackageInfo(mainText = "Delivery charges", secondaryText = "N2,500.0")
                PackageInfo(mainText = "Instant delivery", secondaryText = "N300.0")
                PackageInfo(mainText = "Tax and service charges", secondaryText = "N200.0")
                HorizontalDivider(thickness = 3.dp, color = Color.LightGray, modifier = Modifier.padding(top = 40.dp))
                PackageInfo(mainText = "Package total", secondaryText = "N3000.0")
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)) {
                    OutlinedButton(onClick = { navController.popBackStack() }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = colorResource(id = R.color.main_text_color)), border = BorderStroke(1.dp, colorResource(id = R.color.main_text_color)), shape = RoundedCornerShape(5.dp)) {
                        Text("Edit package")
                    }
                    OutlinedButton(onClick = { navController.navigate("transaction") }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_text_color), contentColor = Color.White), border = BorderStroke(1.dp, colorResource(id = R.color.main_text_color)), shape = RoundedCornerShape(5.dp), modifier = Modifier.padding(start = 30.dp)) {
                        Text("Make transaction")
                    }
                }
            }
            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 130.dp))
        }
    }
}