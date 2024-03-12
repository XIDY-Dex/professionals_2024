package com.student.professionals2024.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.student.professionals2024.R
import com.student.professionals2024.ui.components.LogOutMenuOption
import com.student.professionals2024.ui.components.ProfileScreenMenuOption

@Composable
fun ProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    val balanceVisible = remember {
        mutableStateOf(false)
    }
    val darkThemeEnabled = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize().padding(bottom = paddingValues.calculateBottomPadding() + 5.dp)) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Text("Profile", textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 20.dp), fontSize = 16.sp, color = Color.LightGray)
            HorizontalDivider(thickness = 3.dp, color = Color.LightGray)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)) {
            Image(painter = painterResource(id = R.drawable.placeholder), contentDescription = null, modifier = Modifier
                .size(70.dp)
                .clip(
                    CircleShape
                ), contentScale = ContentScale.Crop)
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 16.dp, top = 10.dp)) {
                Text("Hello user!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text("Current balance: ")
                    Text(text = if(balanceVisible.value) "10000" else "******", color = Color.Blue)
                    Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                        Icon(painterResource(id = if(balanceVisible.value) R.drawable.password_invisible else R.drawable.password_visible), contentDescription = null, modifier = Modifier.clickable { balanceVisible.value = !balanceVisible.value })
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)) {
            Text("Enable dark mode", fontWeight = FontWeight.Normal, fontSize = 16.sp, modifier = Modifier.padding(start = 20.dp, top = 15.dp))
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxWidth()) {
                Switch(checked = darkThemeEnabled.value, onCheckedChange = {
                    darkThemeEnabled.value = it; }, modifier = Modifier.scale(0.7f))
            }
        }
        ProfileScreenMenuOption(mainText = "Edit profile", secondaryText = "Name, phone no, address, email...", icon = R.drawable.profile_circle) {}
        ProfileScreenMenuOption(mainText = "Statements & reports", secondaryText = "Download transaction details, orders, deliveries", icon = R.drawable.sertificate) {}
        ProfileScreenMenuOption(mainText = "Notification Settings", secondaryText = "mute, unmute, set location & tracking setting", icon = R.drawable.notification) { navController.navigate("notifications") }
        ProfileScreenMenuOption(mainText = "Card & Bank account settings", secondaryText = "change cards, delete card details", icon = R.drawable.wallet_icon) { navController.navigate("payment_method")}
        ProfileScreenMenuOption(mainText = "Referrals", secondaryText = "check no of friends and earn", icon = R.drawable.referals_icon) {}
        ProfileScreenMenuOption(mainText = "About us", secondaryText = "know more about us, terms and conditions", icon = R.drawable.about_us_icon) {}
        LogOutMenuOption {}
    }
}
