package com.student.professionals2024.domain.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupabaseUser (
    @SerialName("full_name")
    val FullName: String,
    @SerialName("phone_number")
    val PhoneNumber: String,
    @SerialName("email_address")
    val EmailAddress: String
)