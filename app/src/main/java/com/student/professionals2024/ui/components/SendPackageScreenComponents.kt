package com.student.professionals2024.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.student.professionals2024.domain.models.DestinationDetails

@Composable
fun SendPackageField(inputState: MutableState<String>, hint: String) {
    OutlinedTextField(value = inputState.value, onValueChange = {inputState.value = it}, modifier = Modifier
        .fillMaxWidth().padding(top = 5.dp), placeholder = { Text(hint, fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
}

@Composable
fun DestinationFields(index: Int, destinationElement: MutableList<DestinationDetails>) {
    OutlinedTextField(value = destinationElement[index].address, onValueChange = {destinationElement[index].address = it}, modifier = Modifier
        .fillMaxWidth().padding(top = 5.dp), placeholder = { Text("Address", fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
    OutlinedTextField(value = destinationElement[index].stateCountry, onValueChange = {destinationElement[index].stateCountry = it}, modifier = Modifier
        .fillMaxWidth().padding(top = 5.dp), placeholder = { Text("State,Country", fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
    OutlinedTextField(value = destinationElement[index].phoneNumber, onValueChange = {destinationElement[index].phoneNumber = it}, modifier = Modifier
        .fillMaxWidth().padding(top = 5.dp), placeholder = { Text("Phone number", fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
    OutlinedTextField(value = destinationElement[index].others, onValueChange = {destinationElement[index].others = it}, modifier = Modifier
        .fillMaxWidth().padding(top = 5.dp), placeholder = { Text("Others", fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
}

@Composable
fun DestinationField(destinationValue: String, onValueChanged: (String) -> Unit, hint: String) {
    OutlinedTextField(value = destinationValue, onValueChange = onValueChanged, modifier = Modifier
        .fillMaxWidth().padding(top = 5.dp), placeholder = { Text(hint, fontSize = 14.sp, style = TextStyle(textAlign = TextAlign.Left, fontWeight = FontWeight.Thin)) })
}