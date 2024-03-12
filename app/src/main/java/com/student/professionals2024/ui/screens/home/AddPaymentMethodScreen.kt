package com.student.professionals2024.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.R


@Composable
fun AddPaymentMethod(navigateBack: () -> Unit) {
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .height(100.dp)) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.go_back_icon),
                contentDescription = null,
                tint = colorResource(id = R.color.main_text_color),
                modifier = Modifier.size(24.dp).clickable { navigateBack() }
            )
        }
        Text(
            "Add Payment Method",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp),
            fontSize = 16.sp,
            color = Color.LightGray
        )
        HorizontalDivider(thickness = 3.dp, color = Color.LightGray)
    }
    
}