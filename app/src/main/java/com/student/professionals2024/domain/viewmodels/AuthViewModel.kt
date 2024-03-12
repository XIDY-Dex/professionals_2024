package com.student.professionals2024.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.professionals2024.core.UserState
import com.student.professionals2024.data.db.UserModel
import com.student.professionals2024.domain.models.dto.SupabaseUser
import com.student.professionals2024.domain.repository.DeliveryAppRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repoImpl: DeliveryAppRepoImpl, private val supabaseClient: SupabaseClient): ViewModel() {
    private val _userRegistrationState = MutableStateFlow<UserState>(UserState.Loading)
    val userState = _userRegistrationState.asStateFlow()
    private val _passwordChangeState = MutableStateFlow<UserState>(UserState.Loading)
    val passwordChangeState = _passwordChangeState.asStateFlow()
    private val _userLoginState = MutableStateFlow<UserState>(UserState.Default)
    val userLoginState = _userLoginState.asStateFlow()
    private val _OtpSendState = MutableStateFlow<UserState>(UserState.Loading)
    val otpSendState = _OtpSendState.asStateFlow()
    private val _verificationState = MutableStateFlow<UserState>(UserState.Loading)
    val verificationState = _verificationState.asStateFlow()
    var userEmail: String = ""
    private val _googleSignInProcess = MutableStateFlow<UserState>(UserState.Default)
    val googleSignInProcess = _googleSignInProcess.asStateFlow()


    fun signUpWithEmail(userEmail: String, userPassword: String, phone: String, fullName: String) {
        viewModelScope.launch {
            _userRegistrationState.emit(UserState.Loading)
            try {
                supabaseClient.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                repoImpl.setUserAuthorizationToken(supabaseClient.auth.currentAccessTokenOrNull() ?: "")
                /*val user = SupabaseUser(
                    EmailAddress = userEmail,
                    FullName = fullName,
                    PhoneNumber = phone
                )
                supabaseClient.postgrest.from("users").insert(user)*/
                val newUser = UserModel(fullName = fullName, supabaseUserToken = supabaseClient.auth.currentAccessTokenOrNull() ?: "")
                repoImpl.insertNewUser(newUser)
                _userRegistrationState.emit(UserState.Success(""))
            }
            catch(e: Exception) {
                _userRegistrationState.emit(UserState.Error("Error occured: ${e.message}"))
            }
        }
    }

    fun loginWithEmail(userEmail: String, userPassword: String) {
        viewModelScope.launch {
            _userLoginState.emit(UserState.Loading)
            try {
                supabaseClient.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                repoImpl.setUserAuthorizationToken(supabaseClient.auth.currentAccessTokenOrNull() ?: "")
                _userLoginState.emit(UserState.Success("Logged in successfully!"))
            }
            catch(e: Exception) {
                _userLoginState.emit(UserState.Error("Error: ${e.message}"))
            }
        }
    }

    fun sendOtpCode(userEmail: String) {
        Log.d("OTP", "Sending OTP Code to email $userEmail")
        viewModelScope.launch {
            _OtpSendState.emit(UserState.Loading)
            try {
                supabaseClient.auth.resetPasswordForEmail(userEmail)
                _OtpSendState.emit(UserState.Success("Code sent successfully!"))
            }
            catch(e: Exception) {
                _OtpSendState.emit(UserState.Error("Error: ${e.message}"))
            }
        }
        Log.d("OTP", "Code have been sent")
    }

    fun verifyUserOtpCode(otpCode: String) {
        viewModelScope.launch {
            _verificationState.emit(UserState.Loading)
            try {
                supabaseClient.auth.verifyEmailOtp(email = userEmail, type = OtpType.Email.RECOVERY, token = otpCode)
                _verificationState.emit(UserState.Success("Code verified successfully!"))
            }
            catch(e: Exception) {
                _verificationState.emit(UserState.Error("Error: ${e.message}"))
            }
        }
    }

    fun resetUserPassword(userNewPassword: String) {
        viewModelScope.launch {
            _passwordChangeState.emit(UserState.Loading)
            try {
                viewModelScope.launch {
                    supabaseClient.auth.modifyUser {
                        password = userNewPassword
                    }
                    _passwordChangeState.emit(UserState.Success("Password successfully changed!"))
                }
            }
            catch(e: Exception) {
                _passwordChangeState.emit(UserState.Error("Error: ${e.message}"))
            }
        }
    }

    fun loginWithGoogle(googleIdToken: String, rawNonce: String) {
        viewModelScope.launch {
            try {
                supabaseClient.auth.signInWith(IDToken) {
                    idToken = googleIdToken
                    provider = Google
                }
                val newUser = UserModel(fullName = supabaseClient.auth.currentUserOrNull()?.aud ?: "", supabaseUserToken = supabaseClient.auth.currentAccessTokenOrNull() ?: "")
                repoImpl.insertNewUser(newUser)
            }
            catch(e: Exception) {
                Log.d("GOOGLE", e.message!!)
            }
        }
    }
}