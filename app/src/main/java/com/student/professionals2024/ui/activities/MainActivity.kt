package com.student.professionals2024.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.student.professionals2024.core.UserState
import com.student.professionals2024.domain.viewmodels.AuthViewModel
import com.student.professionals2024.domain.viewmodels.HomeViewModel
import com.student.professionals2024.domain.viewmodels.MainViewModel
import com.student.professionals2024.domain.viewmodels.SendPackageViewModel
import com.student.professionals2024.ui.components.LoadingComponent
import com.student.professionals2024.ui.screens.auth.AuthorizationScreen
import com.student.professionals2024.ui.screens.auth.CreateAccountScreen
import com.student.professionals2024.ui.screens.auth.ForgotPasswordScreen
import com.student.professionals2024.ui.screens.auth.NewPasswordScreen
import com.student.professionals2024.ui.screens.auth.OTPVerificationScreen
import com.student.professionals2024.ui.screens.home.MainHomeScreen
import com.student.professionals2024.ui.screens.onboarding.OnboardingScreen
import com.student.professionals2024.ui.screens.onboarding.splashScreen
import com.student.professionals2024.ui.theme.Professionals2024Theme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel: MainViewModel by viewModels()
    val authViewModel: AuthViewModel by viewModels()
    val homeViewModel: HomeViewModel by viewModels()
    val packageViewModel: SendPackageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Professionals2024Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    val onBoardScreenComplete = mainViewModel.onboardScreenComplete.collectAsState()
                    val (showSplash, closeSplash) = remember {
                        mutableStateOf(true)
                    }

                    LaunchedEffect(key1 = showSplash) {
                        mainViewModel.showSplashScreen().collect {
                            if ( it == "stop") {
                                closeSplash(false)
                                if(onBoardScreenComplete.value!!) navController.navigate("auth")
                                else navController.navigate("onboardScreen")
                            }

                        }
                    }
                    NavHost(navController = navController, startDestination = "splashScreen") {
                        composable("splashScreen") {
                            AnimatedVisibility(visible = showSplash, enter = fadeIn(), exit = fadeOut()) {
                                splashScreen()
                            }
                        }
                        composable("onboardScreen") {
                            val screens = mainViewModel.screens.collectAsState()
                            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
                            OnboardingScreen(
                                currentOnboardInfoScreen = currentScreen.value,
                                onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                                moveToLoginScreen = {
                                    mainViewModel.completeScreenOnboarding()
                                    navController.navigate("logInScreen") },
                                screensLeft = screens.value
                            )
                        }
                        navigation(
                            startDestination = "logInScreen",
                            route = "auth"
                        ) {
                            val authCompleted = {
                                navController.navigate("main") {
                                    popUpTo("auth") {
                                        inclusive = true
                                    }
                                }
                            }
                            composable("logInScreen") {
                                val userAuthorizationState = authViewModel.userLoginState.collectAsState()
                                when(userAuthorizationState.value) {
                                    is UserState.Success -> authCompleted()
                                    is UserState.Error -> {
                                        val context = LocalContext.current
                                        Toast.makeText(context, (userAuthorizationState.value as UserState.Error).message, Toast.LENGTH_LONG).show()
                                    }
                                    is UserState.Loading -> LoadingComponent()
                                    else -> {}
                                }
                                AuthorizationScreen(
                                    onLogInButtonPressed = authViewModel::loginWithEmail,
                                    onSignUpButtonPressed = { navController.navigate("registrationScreen")},
                                    forgotPasswordClick = { navController.navigate("forgot_password") },
                                    viewModel = authViewModel,
                                    navController = navController
                                )
                            }
                            composable("registrationScreen") {
                                val userAuthorizationState = authViewModel.userState.collectAsState()
                                val context = LocalContext.current
                                when(userAuthorizationState.value) {
                                    is UserState.Success -> navController.navigate("logInScreen")
                                    is UserState.Error -> Toast.makeText(context, (userAuthorizationState.value as UserState.Error).message, Toast.LENGTH_LONG).show()
                                    else -> {}
                                }
                                CreateAccountScreen(
                                    onSignUpButtonPressed = authViewModel::signUpWithEmail,
                                    returnToLoginScreen = {
                                        navController.navigate("logInScreen") {
                                            popUpTo("registrationScreen")
                                        } },
                                    viewModel = authViewModel,
                                    navController = navController
                                )
                            }
                            composable("forgotPassword") {
                                val passwordState = authViewModel.otpSendState.collectAsState()
                                val context = LocalContext.current
                                when(passwordState.value) {
                                    is UserState.Error -> {
                                        Toast.makeText(context, (passwordState.value as UserState.Error).message, Toast.LENGTH_LONG).show()
                                    }
                                    else -> {}
                                }
                                ForgotPasswordScreen {email ->
                                    authViewModel.sendOtpCode(email)
                                    authViewModel.userEmail = email
                                    navController.navigate("otp_verification")
                                }
                            }
                            composable("otp_verification") {
                                val email = authViewModel.userEmail
                                val otpCorrect = remember { mutableStateOf(true) }
                                val otpVerificationState = authViewModel.verificationState.collectAsState()
                                val context = LocalContext.current
                                when(otpVerificationState.value) {
                                    is UserState.Success -> navController.navigate("new_password")
                                    is UserState.Error -> {
                                        otpCorrect.value = false
                                        Toast.makeText(context, (otpVerificationState.value as UserState.Error).message, Toast.LENGTH_LONG).show()
                                    }
                                    else -> {}
                                }
                                OTPVerificationScreen(otpCorrect.value, authViewModel::verifyUserOtpCode)
                            }
                            composable("new_password") {
                                val passwordChangeState = authViewModel.passwordChangeState.collectAsState()
                                val context = LocalContext.current
                                when(passwordChangeState.value) {
                                    is UserState.Success -> navController.navigate("logInScreen")
                                    is UserState.Error -> {
                                        Toast.makeText(context, (passwordChangeState.value as UserState.Error).message, Toast.LENGTH_LONG).show()
                                    }
                                    else -> {}
                                }
                                NewPasswordScreen {
                                    authViewModel::resetUserPassword
                                    navController.navigate("logInScreen")
                                }
                            }
                        }
                        composable("main") {
                            MainHomeScreen(homeViewModel = homeViewModel, sendPackageViewModel = packageViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel<T>(parentEntry)
}