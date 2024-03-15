package com.student.professionals2024.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.student.professionals2024.R
import com.student.professionals2024.domain.viewmodels.HomeViewModel
import com.student.professionals2024.domain.viewmodels.SendPackageViewModel
import com.student.professionals2024.ui.components.BottomNavigationElement
import com.student.professionals2024.ui.components.HomeMenuItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainHomeScreen(homeViewModel: HomeViewModel, sendPackageViewModel: SendPackageViewModel) {
    val nestedNavController = rememberNavController()
    val bottomNavigationScreens = listOf(
        BottomNavigationElement("Home", R.drawable.home_icon),
        BottomNavigationElement("Wallet", R.drawable.wallet_icon),
        BottomNavigationElement("Track", R.drawable.track_icon),
        BottomNavigationElement("Profile", R.drawable.profile_circle)
    )
    val selectedBottomNavigationElement = rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavigationScreens.forEachIndexed { index, element ->
                    NavigationBarItem(
                        selected = index == selectedBottomNavigationElement.intValue,
                        onClick = {
                            nestedNavController.navigate(element.title.lowercase()); selectedBottomNavigationElement.intValue =
                            index
                        },
                        icon = {
                            Icon(
                                painterResource(id = element.iconDefault), contentDescription = null
                            )
                        },
                        alwaysShowLabel = true,
                        label = { Text(element.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorResource(id = R.color.main_text_color),
                            indicatorColor = colorResource(id = R.color.main_text_color).copy(alpha = 0.2f)
                        )
                    )
                }
            }
        }
    ) {paddings ->
        NavHost(navController = nestedNavController, startDestination = "home") {
            composable("home") {
                Row(modifier = Modifier.fillMaxSize()) {
                    HomeMenuItem(
                        mainText = "Send a package",
                        secondaryText = "Request for a driver to pick up or deliver your package for you",
                        icon = R.drawable.package_icon
                    ) { nestedNavController.navigate("package")}
                    HomeMenuItem(
                        mainText = "Chats",
                        secondaryText = "Search for availible rider in your area",
                        icon = R.drawable.ic_chat
                    ) {
                        nestedNavController.navigate("chatsList")
                    }
                }
            }
            composable("wallet") {
                Text(text = "Wallet")
            }
            composable("track") {
                Text(text = "Track")
            }
            composable("profile") {
                ProfileScreen(nestedNavController, paddings)
            }
            composable("payment_method") {
                AddPaymentMethod {
                    nestedNavController.popBackStack()
                }
            }
            composable("notifications") {
                NotificationScreen {
                    nestedNavController.popBackStack()
                }
            }
            navigation("send_package", route = "package") {
                composable("send_package") {
                    SendPackageScreen(paddings, nestedNavController,sendPackageViewModel)
                }
                composable("confirm_package") {
                    PackageSendingConfirmation(sendPackageViewModel, nestedNavController, paddings)
                }
                composable("transaction") {
                    TransactionScreen(sendPackageViewModel, nestedNavController)
                }
            }
            composable("chatsList") {
                ChatListScreen()
            }
            composable("chat/{chatId}", arguments = listOf(navArgument("chatId") { type = NavType.IntType})) {

            }
        }
    }
}