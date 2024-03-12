package com.student.professionals2024.domain.models

data class PackageInformation(
    val originAddress: String,
    val originCountryState: String,
    val originPhoneNumber: String,
    val originOthers: String,
    val destinations: List<DestinationDetails>,
    val itemsDescription: String,
    val itemsWeight: String,
    val itemsWorth: String,
    var trackNumber: String = ""
)

fun setTrackNumber(): String {
    val numbers = mutableListOf<String>()
    repeat(4) {
        numbers.add((1000..9999).random().toString())
    }
    return "R-${numbers.joinToString("-")}"
}