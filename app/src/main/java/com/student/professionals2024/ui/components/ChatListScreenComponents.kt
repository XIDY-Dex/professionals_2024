package com.student.professionals2024.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.R

@Composable
fun ChatListElement(userName: String, lastMessageText: String, newMessagesCount: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(90.dp)
        .padding(top = 10.dp), shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color.White), border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.placeholder), contentDescription = null, modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape), contentScale = ContentScale.Crop)
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp), verticalArrangement = Arrangement.Center) {
                    Text(userName, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    Text(lastMessageText, fontSize = 12.sp, fontWeight = FontWeight.Normal, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.width(200.dp))
                }
                if(newMessagesCount > 0) {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = R.color.main_text_color)), contentAlignment = Alignment.Center) {
                        Text("1", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatTextElement(userWroteMessage: Boolean = true, text: String) {
    Card(modifier = Modifier
        .padding(top = 10.dp, start = 20.dp, end = 20.dp), colors = CardDefaults.cardColors(containerColor = colorResource(
        id = if(userWroteMessage) R.color.main_text_color else R.color.secondary_text_color
    ), contentColor = if(userWroteMessage) Color.White else Color.Black), shape = RoundedCornerShape(4.dp),
    ) {
        Text(text, fontWeight = FontWeight.Medium, fontSize = 12.sp, modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Max)
            .padding(start = 10.dp, end = 10.dp))
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            ChatTextElement(text = "Привет! Для твоей группы не найдено актуальных замещений")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            ChatTextElement(text = "Да ты заебал!", userWroteMessage = false)
        }
    }
}