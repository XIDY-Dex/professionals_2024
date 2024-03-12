package com.student.professionals2024.domain.repository

import com.student.professionals2024.data.datastore.DataStoreManager
import com.student.professionals2024.data.db.AppDatabase
import com.student.professionals2024.data.db.UserDao
import com.student.professionals2024.data.db.UserModel
import com.student.professionals2024.data.repository.DeliveryAppRepository
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeliveryAppRepoImpl @Inject constructor(private val dataStoreManager: DataStoreManager, private val supabaseClient: SupabaseClient, database: AppDatabase): DeliveryAppRepository {
    private val userDao: UserDao = database.getUserDao()
    override suspend fun setOnboardingScreenCompletion(state: Boolean) {
        dataStoreManager.setOnboardScreenComplete(state)
    }

    override fun getOnboardScreenCompletion(): Flow<Boolean> {
        return dataStoreManager.getOnboardScreenCompletion()
    }

    override suspend fun setUserAuthorizationToken(token: String) {
        dataStoreManager.saveUserAuthorizationToken(token)
    }

    override suspend fun insertNewUser(user: UserModel) {
        userDao.insertNewUser(user)
    }

    override suspend fun deleteUser(user: UserModel) {
        userDao.deleteUser(user)
    }

    override suspend fun updateUser(user: UserModel) {
        userDao.updateUser(user)
    }

    override suspend fun getUserByFullName(name: String): Flow<UserModel> {
        return userDao.getUserByName(name)
    }

    override suspend fun getUserBySupabaseToken(id: String): Flow<UserModel> {
        return userDao.getUserBySupabaseToken(id)
    }
}