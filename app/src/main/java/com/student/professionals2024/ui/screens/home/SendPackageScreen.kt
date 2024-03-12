package com.student.professionals2024.ui.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.student.professionals2024.R
import com.student.professionals2024.domain.models.DestinationDetails
import com.student.professionals2024.domain.models.PackageInformation
import com.student.professionals2024.domain.models.setTrackNumber
import com.student.professionals2024.domain.viewmodels.SendPackageViewModel
import com.student.professionals2024.ui.components.DeliveryTypeCard
import com.student.professionals2024.ui.components.DestinationField
import com.student.professionals2024.ui.components.SendPackageField

@Composable
fun SendPackageScreen(paddingValues: PaddingValues, navController: NavController, viewModel: SendPackageViewModel) {
    val originAddress = rememberSaveable { mutableStateOf("") }
    val originStateCountry = rememberSaveable { mutableStateOf("") }
    val originPhoneNumber = rememberSaveable { mutableStateOf("") }
    val originOthers = rememberSaveable { mutableStateOf("") }
    val destinationAmounts = rememberSaveable { mutableIntStateOf(1) }
    val destinationsDetails = remember { mutableStateListOf<DestinationDetails>() }

    val packageItems = rememberSaveable { mutableStateOf("") }
    val weightOfItems = rememberSaveable { mutableStateOf("") }
    val worthOfItems = rememberSaveable { mutableStateOf("") }
    val deliveryType = remember { mutableIntStateOf(0) }

    val moveToNextScreen = remember {
        derivedStateOf {
            originAddress.value.isNotEmpty() && originAddress.value.isNotEmpty()
                    && originStateCountry.value.isNotEmpty() && destinationsDetails.all { it.checkAllIsNotEmpty() }
                    && deliveryType.intValue != 0 && packageItems.value.isNotEmpty() && weightOfItems.value.isNotEmpty() && weightOfItems.value.isNotEmpty()
        }
    }
    if(moveToNextScreen.value) {
        LaunchedEffect(key1 = true) {
            val packageInfo = PackageInformation(
                originAddress = originAddress.value,
                originCountryState = originStateCountry.value,
                originPhoneNumber = originPhoneNumber.value,
                originOthers = originOthers.value,
                destinations = destinationsDetails.toList(),
                itemsDescription = packageItems.value,
                itemsWeight = weightOfItems.value,
                itemsWorth = worthOfItems.value,
                trackNumber = setTrackNumber()
            )
            Log.d("DELIVERY", destinationsDetails.toString())
            viewModel.setPackageInfo(packageInfo)
            navController.navigate("confirm_package") {
                launchSingleTop = true
                restoreState = true
            }
        }
    }


    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .height(100.dp)) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.go_back_icon),
                contentDescription = null,
                tint = colorResource(id = R.color.main_text_color),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { }
            )
        }
        Text(
            "Send a package",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp),
            fontSize = 16.sp,
            color = Color.LightGray
        )
        HorizontalDivider(thickness = 3.dp, color = Color.LightGray)
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp, start = 24.dp, end = 24.dp), horizontalAlignment = Alignment.Start) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(painterResource(id = R.drawable.origin_icon), contentDescription = null, modifier = Modifier.size(16.dp), tint = colorResource(
                    id = R.color.main_text_color
                ))
                Text("Origin details", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 5.dp), fontSize = 14.sp)
            }
            SendPackageField(inputState = originAddress, hint = "Address")
            SendPackageField(inputState = originStateCountry, hint = "State,Country")
            SendPackageField(inputState = originPhoneNumber, hint = "Phone")
            SendPackageField(inputState = originOthers, hint = "Others")
            repeat(destinationAmounts.intValue) { index ->
                Log.d("DELIVERY", "CURRENT BUILDING INDEX $index")
                if(destinationsDetails.size < destinationAmounts.intValue) {
                    Log.d("DELIVERY", "CREATED NEW DESTINATION AT INDEX $index")
                    destinationsDetails.add(DestinationDetails("", "", "", ""))
                    Log.d("DELIVERY", destinationsDetails.lastIndex.toString())
                }
                Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(top = 10.dp)) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp), tint = colorResource(
                        id = R.color.main_text_color
                    ))
                    Text("Destination details", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 5.dp))
                }
                if(index <= destinationsDetails.size) {
                    DestinationField(destinationValue = destinationsDetails[index].address, onValueChanged = {destinationsDetails[index] = destinationsDetails[index].copy(address = it)}, hint = "Address")
                    DestinationField(destinationValue = destinationsDetails[index].stateCountry, onValueChanged = { destinationsDetails[index] = destinationsDetails[index].copy(stateCountry = it)}, hint = "State,Country")
                    DestinationField(destinationValue = destinationsDetails[index].phoneNumber, onValueChanged = { destinationsDetails[index] = destinationsDetails[index].copy(phoneNumber = it)}, hint = "Phone number")
                    DestinationField(destinationValue = destinationsDetails[index].others, onValueChanged = {destinationsDetails[index] = destinationsDetails[index].copy(others = it)}, hint = "Others")
                    Log.d("DELIVERY", destinationsDetails[index].address)
                }
            }

            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier
                .padding(top = 10.dp)
                .clickable { destinationAmounts.intValue++ }) {
                Icon(painterResource(id = R.drawable.add_square), contentDescription = null, modifier = Modifier.size(16.dp), tint = colorResource(
                    id = R.color.main_text_color
                ))
                Text("Add destination", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 5.dp))
            }
            Text("Package Detail", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 10.dp))
            SendPackageField(inputState = packageItems, hint = "Package items")
            SendPackageField(inputState = weightOfItems, hint = "Weight of items")
            SendPackageField(inputState = worthOfItems, hint = "Worth of items")
            Text("Select delivery type", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 10.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                DeliveryTypeCard(icon = ImageVector.vectorResource(R.drawable.clock), text = "Instant delivery", selected = deliveryType.intValue == 1) { deliveryType.intValue = 1}
                DeliveryTypeCard(icon = Icons.Default.DateRange, text = "Scheduled delivery", selected = deliveryType.intValue == 2) { deliveryType.intValue = 2}
            }
            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding() + 30.dp))
        }
    }
}