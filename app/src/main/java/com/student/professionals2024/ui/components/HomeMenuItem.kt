package com.student.professionals2024.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.R

@Composable
fun HomeMenuItem(mainText: String, secondaryText: String, icon: Int, onCardClick: () -> Unit) {
    ElevatedCard(modifier = Modifier
        .size(160.dp),
        onClick = { onCardClick() },
        colors = CardDefaults.elevatedCardColors(containerColor = colorResource(id = R.color.card_default_color), contentColor = colorResource(
            id = R.color.main_text_color
        )), elevation = CardDefaults.elevatedCardElevation(12.dp), shape = CardDefaults.elevatedShape) {
        Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier
            .padding(top = 30.dp, start = 10.dp)
            .size(40.dp))
        Text(mainText, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 13.dp, top = 5.dp), fontSize = 16.sp)
        Text(secondaryText, fontWeight = FontWeight.Thin, modifier = Modifier.padding(start = 13.dp, top = 5.dp), fontSize = 8.sp, maxLines = 2)
    }
}

@Preview
@Composable
fun HomeMenuItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        HomeMenuItem(
            mainText = "Send a package",
            secondaryText = "Request for a driver to pick up or deliver your package for you",
            icon = R.drawable.package_icon
        ) {}
    }
}