package com.student.professionals2024.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.R

@Composable
fun PackageInfo(mainText: String, secondaryText: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween ,modifier = Modifier
        .fillMaxWidth()
        .padding(top = 7.dp)) {
        Text(mainText, fontSize = 12.sp, fontWeight = FontWeight.Light, color = Color.LightGray)
        Text(secondaryText, fontSize = 12.sp, fontWeight = FontWeight.Light, color = colorResource(
            id = R.color.package_item_value_color
        )
        )
    }
}