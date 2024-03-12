package com.student.professionals2024.core

sealed class UserState {
    data object Default : UserState()
    data object Loading : UserState()
    data class Success(val message: String): UserState()
    data class Error(val message: String): UserState()
}