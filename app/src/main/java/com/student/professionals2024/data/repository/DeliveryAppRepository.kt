package com.student.professionals2024.data.repository

import com.student.professionals2024.data.db.UserModel
import kotlinx.coroutines.flow.Flow

interface DeliveryAppRepository {
    suspend fun setOnboardingScreenCompletion(state: Boolean)
    fun getOnboardScreenCompletion() : Flow<Boolean>
    suspend fun setUserAuthorizationToken(token: String)
    suspend fun insertNewUser(user: UserModel)
    suspend fun deleteUser(user: UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun getUserByFullName(name: String): Flow<UserModel>
    suspend fun getUserBySupabaseToken(id: String): Flow<UserModel>
}