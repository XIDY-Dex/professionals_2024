package com.student.professionals2024.domain.models.dto

import com.student.professionals2024.domain.models.DestinationDetails

data class DestinationDto(
    var trackNumber: String,
    var address: String,
    var stateCountry: String,
    var phoneNumber: String,
    var others: String
)
fun fromDestination(destination: DestinationDetails, trackNumber: String): DestinationDto {
    return DestinationDto(
        trackNumber = trackNumber,
        address = destination.address,
        stateCountry = destination.stateCountry,
        phoneNumber = destination.phoneNumber,
        others = destination.others
    )
}
