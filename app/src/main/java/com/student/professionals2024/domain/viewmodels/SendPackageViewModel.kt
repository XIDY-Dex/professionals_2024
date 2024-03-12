package com.student.professionals2024.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.professionals2024.core.UserState
import com.student.professionals2024.domain.models.PackageInformation
import com.student.professionals2024.domain.models.dto.DestinationDto
import com.student.professionals2024.domain.models.dto.fromDestination
import com.student.professionals2024.domain.models.dto.fromPackageInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendPackageViewModel @Inject constructor(private val supabaseClient: SupabaseClient): ViewModel() {
    private val _packageInfo = MutableStateFlow<PackageInformation?>(null)
    val packageInfo = _packageInfo.asStateFlow()

    private val _orderTransactionState = MutableStateFlow<UserState>(UserState.Default)
    val orderTransactionState = _orderTransactionState.asStateFlow()

    fun setPackageInfo(sendPackage: PackageInformation) {
        _packageInfo.value = sendPackage
    }

    suspend fun makeTransaction() {
        val order = fromPackageInfo(_packageInfo.value ?: PackageInformation("", "", "", "", emptyList(), "", "", ""))
        val destinations = _packageInfo.value?.destinations?.map {
            fromDestination(it, _packageInfo.value?.trackNumber ?: "")
        } ?: emptyList<DestinationDto>()
        viewModelScope.launch {
            _orderTransactionState.emit(UserState.Loading)
            try {
                supabaseClient.from("orders").insert(order)
                supabaseClient.from("destinations").insert(destinations)
                _orderTransactionState.emit(UserState.Success(""))
            }
            catch(e: Exception) {
                _orderTransactionState.emit(UserState.Error(""))
                Log.d("DELIVERY", e.message!!)
            }
        }
    }
}