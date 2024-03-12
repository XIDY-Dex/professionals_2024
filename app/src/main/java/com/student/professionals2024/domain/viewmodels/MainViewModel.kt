package com.student.professionals2024.domain.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.professionals2024.R
import com.student.professionals2024.data.datastore.DataStoreManager
import com.student.professionals2024.domain.models.OnboardingScreens
import com.student.professionals2024.domain.repository.DeliveryAppRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repoImpl: DeliveryAppRepoImpl): ViewModel() {
    private val _screens = MutableStateFlow(
        LinkedList(
        mutableListOf(
            OnboardingScreens(
                R.drawable.onboard1,
                "Quick delivery at your doorstep",
                "Enjoy quick pick-up and delivery to your destination"
            ),
            OnboardingScreens(
                R.drawable.onboard2,
                "Flexible payment",
                "Different modes of payment either before and after delivery without stress"
            ),
            OnboardingScreens(
                R.drawable.onboard3,
                "Real-time tracking",
                "Track your packages/items from the comfort of your home till final destination"
            )
        )
    ))
    val screens = MutableStateFlow(_screens.value.size)
    private val _currentOnboardingScreen = MutableStateFlow<OnboardingScreens>(_screens.value.remove())
    val currentOnboardingScreen = _currentOnboardingScreen.asStateFlow()
    private var _onBoardComplete = MutableStateFlow<Boolean?>(null)
    val onboardScreenComplete = _onBoardComplete.asStateFlow()

    fun nextOnboardingScreen() {
        viewModelScope.launch {
            _currentOnboardingScreen.emit(_screens.value.remove())
            screens.emit(_screens.value.size)
        }
    }
    init {
        getOnboardScreenCompletion()
    }
    fun completeScreenOnboarding() {
        viewModelScope.launch {
            repoImpl.setOnboardingScreenCompletion(true)
        }
    }
    
    private fun getOnboardScreenCompletion() {
        viewModelScope.launch {
            repoImpl.getOnboardScreenCompletion().collect {
                _onBoardComplete.emit(it)
            }
        }
    }

    fun showSplashScreen(): Flow<String> {
        return flow {
            delay(1000L)
            emit("stop")
        }
    }
}