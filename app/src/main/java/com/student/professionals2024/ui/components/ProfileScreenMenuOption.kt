package com.student.professionals2024.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.student.professionals2024.R

@Composable
fun ProfileScreenMenuOption(mainText: String, secondaryText: String, icon: Int, onClick: () -> Unit) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .height(70.dp)
        .padding(start = 10.dp, end = 10.dp, top = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.padding(top = 1.dp))
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Text(mainText, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 15.dp))
                Text(secondaryText, fontWeight = FontWeight.Thin, modifier = Modifier.padding(start = 15.dp))
            }
            Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, modifier = Modifier.clickable { onClick() })
            }
        }
    }
}

@Composable
fun LogOutMenuOption(mainText: String = "Log out", onClick: () -> Unit) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .height(65.dp)
        .padding(start = 10.dp, end = 10.dp, top = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.log_out_icon), contentDescription = null, modifier = Modifier.padding(top = 1.dp), tint = Color.Red)
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Text(mainText, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 15.dp))
            }
            Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, modifier = Modifier.clickable { onClick() })
            }
        }
    }
}