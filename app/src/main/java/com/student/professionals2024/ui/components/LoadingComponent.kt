package com.student.professionals2024.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.student.professionals2024.R

@Composable
fun LoadingComponent() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(120.dp).align(Alignment.CenterHorizontally),
            strokeWidth = 6.dp,
            color = colorResource(id = R.color.loading_component_color)
        )
    }
}
