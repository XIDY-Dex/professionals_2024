package com.student.professionals2024.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentCardsModel(
    @PrimaryKey(autoGenerate = true)
    val paymentMethodId: Int,
    @ColumnInfo(name = "card_number")
    val cardNumber: String,

)
