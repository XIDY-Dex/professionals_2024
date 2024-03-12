package com.student.professionals2024.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "fullName")
    val fullName: String,
    @ColumnInfo(name = "balance")
    val balance: Int = 0,
    @ColumnInfo(name="supabaseUserToken")
    val supabaseUserToken: String
)