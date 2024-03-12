package com.student.professionals2024

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.student.professionals2024.domain.viewmodels.MainViewModel
import com.student.professionals2024.ui.screens.onboarding.OnboardingScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OnboardScreenTests {
    private lateinit var mainViewModel: MainViewModel
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_correct_screens_are_shown() {
        composeTestRule.setContent {
            mainViewModel = viewModel<MainViewModel>()
            val screens = mainViewModel.screens.collectAsState()
            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboardScreen") {
                composable("onboardScreen") {
                    OnboardingScreen(
                        currentOnboardInfoScreen = currentScreen.value,
                        onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                        moveToLoginScreen = { navController.navigate("logInScreen") },
                        screensLeft = screens.value
                    )
                }
                composable("logInScreen") {
                    Text("Авторизация")
                }
            }
        }
            //Правильные тесты
            composeTestRule.onNodeWithText("Quick delivery at your doorstep").assertIsDisplayed()
            composeTestRule.onNodeWithText("Enjoy quick pick-up and delivery to your destination")
                .assertIsDisplayed()
            composeTestRule.onNodeWithTag(R.drawable.onboard1.toString()).assertIsDisplayed()
            composeTestRule.onNodeWithText("Next").performClick()

            composeTestRule.onNodeWithText("Flexible payment").assertIsDisplayed()
            composeTestRule.onNodeWithText("Different modes of payment either before and after delivery without stress")
                .assertIsDisplayed()
            composeTestRule.onNodeWithTag(R.drawable.onboard2.toString()).assertIsDisplayed()
            composeTestRule.onNodeWithText("Next").performClick()

            composeTestRule.onNodeWithText("Real-time tracking").assertIsDisplayed()
            composeTestRule.onNodeWithText("Track your packages/items from the comfort of your home till final destination")
                .assertIsDisplayed()
            composeTestRule.onNodeWithTag(R.drawable.onboard3.toString()).assertIsDisplayed()

            //Должны заваливаться
            composeTestRule.onNodeWithText("Quick delivery at your doorstep").assertIsNotDisplayed()
            composeTestRule.onNodeWithText("Enjoy quick pick-up and delivery to your destination")
                .assertIsNotDisplayed()
            composeTestRule.onNodeWithTag(R.drawable.onboard1.toString()).assertIsNotDisplayed()
    }

    @Test
    fun test_check_button_shows_correct_text() {
        composeTestRule.setContent {
            mainViewModel = viewModel<MainViewModel>()
            val screens = mainViewModel.screens.collectAsState()
            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboardScreen") {
                composable("onboardScreen") {
                    OnboardingScreen(
                        currentOnboardInfoScreen = currentScreen.value,
                        onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                        moveToLoginScreen = { navController.navigate("logInScreen") },
                        screensLeft = screens.value
                    )
                }
                composable("logInScreen") {
                    Text("Авторизация")
                }
            }
        }

        assert(mainViewModel.screens.value > 0)
        composeTestRule.onNodeWithText("Next").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").performClick()
        assert(mainViewModel.screens.value > 0)
        composeTestRule.onNodeWithText("Next").assertIsDisplayed()
        composeTestRule.onNodeWithText("Next").performClick()
        assert(mainViewModel.screens.value == 0)
        composeTestRule.onNodeWithText("Next").assertIsNotDisplayed()
    }

    @Test
    fun test_signup_button_shows() {
        composeTestRule.setContent {
            mainViewModel = viewModel<MainViewModel>()
            val screens = mainViewModel.screens.collectAsState()
            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboardScreen") {
                composable("onboardScreen") {
                    OnboardingScreen(
                        currentOnboardInfoScreen = currentScreen.value,
                        onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                        moveToLoginScreen = { navController.navigate("logInScreen") },
                        screensLeft = screens.value
                    )
                }
                composable("logInScreen") {
                    Text("Авторизация")
                }
            }
        }

        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Sign up").assertIsDisplayed()
    }

    @Test
    fun test_skip_button_navigate_to_login_screen() {
        composeTestRule.setContent {
            mainViewModel = viewModel<MainViewModel>()
            val screens = mainViewModel.screens.collectAsState()
            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboardScreen") {
                composable("onboardScreen") {
                    OnboardingScreen(
                        currentOnboardInfoScreen = currentScreen.value,
                        onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                        moveToLoginScreen = { navController.navigate("logInScreen") },
                        screensLeft = screens.value
                    )
                }
                composable("logInScreen") {
                    Text("Регистрация")
                }
            }
        }
        composeTestRule.onNodeWithText("Skip").performClick()
        composeTestRule.onNodeWithText("Регистрация").assertIsDisplayed()
    }

    @Test
    fun test_log_in_button_navigates_to_loginscreen() {
        composeTestRule.setContent {
            mainViewModel = viewModel<MainViewModel>()
            val screens = mainViewModel.screens.collectAsState()
            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboardScreen") {
                composable("onboardScreen") {
                    OnboardingScreen(
                        currentOnboardInfoScreen = currentScreen.value,
                        onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                        moveToLoginScreen = { navController.navigate("logInScreen") },
                        screensLeft = screens.value
                    )
                }
                composable("logInScreen") {
                    Text("Регистрация")
                }
            }
        }
        composeTestRule.onNodeWithText("Next").performClick().performClick()
        composeTestRule.onNodeWithText("Sign up").performClick()
        composeTestRule.onNodeWithText("Регистрация").assertIsDisplayed()
    }

    @Test
    fun test_queue_decremented_correctly() {
        composeTestRule.setContent {
            mainViewModel = viewModel<MainViewModel>()
            val screens = mainViewModel.screens.collectAsState()
            val currentScreen = mainViewModel.currentOnboardingScreen.collectAsState()
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "onboardScreen") {
                composable("onboardScreen") {
                    OnboardingScreen(
                        currentOnboardInfoScreen = currentScreen.value,
                        onNextButtonPressed = { mainViewModel.nextOnboardingScreen() },
                        moveToLoginScreen = { navController.navigate("logInScreen") },
                        screensLeft = screens.value
                    )
                }
                composable("logInScreen") {
                    Text("Авторизация")
                }
            }
        }
        Log.d("SCREENS_LEFT", mainViewModel.screens.value.toString())
        assert(mainViewModel.screens.value == 3) //Перед открытием все экраны в очереди, когда открывается onboardScreen - первый сразу вытаскивается из очереди
        composeTestRule.onNodeWithText("Next").performClick()
        assert(mainViewModel.screens.value == 1)
        assert(mainViewModel.screens.value != 2)
        composeTestRule.onNodeWithText("Next").performClick()
        assert(mainViewModel.screens.value == 0)
    }
}