package com.student.professionals2024.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DestinationDetails(
    var address: String,
    var stateCountry: String,
    var phoneNumber: String,
    var others: String
) : Parcelable {
    fun checkAllIsNotEmpty(): Boolean {
        return address.isNotEmpty() && stateCountry.isNotEmpty() && phoneNumber.isNotEmpty()
    }
}