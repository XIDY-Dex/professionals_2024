package com.student.professionals2024.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.R

@Preview
@Composable
fun WalletScreen() {
    var balanceVisible by remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                "Wallet",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp),
                fontSize = 16.sp,
                color = Color.LightGray
            )
            HorizontalDivider(thickness = 3.dp, color = Color.LightGray)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 50.dp)
            ) {
                Text("Hello user!", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text("Current balance: ")
                    Text(text = if (balanceVisible) "10000" else "******", color = Color.Blue)
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painterResource(id = if (balanceVisible) R.drawable.password_invisible else R.drawable.password_visible),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                balanceVisible = !balanceVisible
                            }
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier
            .width(340.dp)
            .height(115.dp)
            .background(color = Color.LightGray)
            .align(Alignment.CenterHorizontally)) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Top up", fontWeight = FontWeight.SemiBold)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 35.dp), horizontalArrangement = Arrangement.Center) {
                    TransactionType(icon = R.drawable.bank_icon, subText = "Bank")
                    TransactionType(icon = R.drawable.transfer_icon, subText = "Transfer")
                    TransactionType(icon = R.drawable.credit_card_icon, subText = "Card")
                }
            }
        }
        Text("Transaction History", fontWeight = FontWeight.SemiBold, color = colorResource(id = R.color.secondary_text_color), modifier = Modifier.padding(start = 24.dp, top = 20.dp), fontSize = 18.sp)
        Column(modifier = Modifier.padding(top = 10.dp)) {
            TransactionHistoryItem(amount = "10.000р", positive = true, infoText = "Зачисление с карты", date = "7 июля 2022")
            TransactionHistoryItem(amount = "-3999р.", positive = false, infoText = "Оплата заказа", date = "2 июля, 2022")
        }
    }
}

@Composable
fun TransactionType(icon: Int, subText: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(top = 20.dp, end = 30.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(color = colorResource(id = R.color.main_text_color)), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = icon), contentDescription = null, colorFilter = ColorFilter.tint(color = Color.White))
        }
        Text(subText, fontSize = 12.sp)
    }
}


@Composable
fun TransactionHistoryItem(amount: String, positive: Boolean, infoText: String, date: String) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .height(55.dp)
        .padding(start = 24.dp, end = 24.dp, top = 5.dp)
        .background(color = Color.White), elevation = CardDefaults.elevatedCardElevation(8.dp), shape = RoundedCornerShape(3.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Text(amount, color = if(positive) Color.Green else Color.Red, fontSize = 19.sp, modifier = Modifier.padding(start = 5.dp))
                Text(infoText, fontSize = 12.sp, modifier = Modifier.padding(top = 10.dp, start = 5.dp))
            }
            Text(date, textAlign = TextAlign.End, fontSize = 12.sp, color = Color.LightGray)
        }
    }
}