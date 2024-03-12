package com.student.professionals2024.domain.models.dto


import com.student.professionals2024.domain.models.PackageInformation

data class PackageDto(
    val originAddress: String,
    val originCountryState: String,
    val originPhoneNumber: String,
    val originOthers: String,
    val itemsDescription: String,
    val itemsWeight: String,
    val itemsWorth: String,
    var trackNumber: String = ""
) {
}
fun fromPackageInfo(packageInfo: PackageInformation): PackageDto {
    return PackageDto(
        originAddress = packageInfo.originAddress,
        originCountryState = packageInfo.originCountryState,
        originPhoneNumber = packageInfo.originPhoneNumber,
        originOthers = packageInfo.originOthers,
        itemsDescription = packageInfo.itemsDescription,
        itemsWeight = packageInfo.itemsWeight,
        itemsWorth = packageInfo.itemsWorth,
        trackNumber = packageInfo.trackNumber
    )
}