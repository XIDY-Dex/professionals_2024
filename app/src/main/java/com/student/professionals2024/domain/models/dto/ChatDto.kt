package com.student.professionals2024.domain.models.dto

import io.github.jan.supabase.realtime.PrimaryKey

data class ChatDto(
    val id: Int,
    val user_name: String,
    val user_id: Int,
    val last_message: String
)