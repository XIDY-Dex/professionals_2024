package com.student.professionals2024.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.student.professionals2024.R


@Composable
fun DeliveryTypeCard(icon: ImageVector, text: String, selected: Boolean, onCardClick: () -> Unit) {
    ElevatedCard(
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = if(selected) colorResource(id = R.color.main_text_color).copy(alpha = 0.8f) else Color.White, contentColor = Color.LightGray),
        modifier = Modifier
            .width(150.dp)
            .height(75.dp)
            .padding(end = 10.dp),
        onClick = onCardClick
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
            Icon(imageVector = icon, contentDescription = null)
            Text(text)
        }
    }
}
