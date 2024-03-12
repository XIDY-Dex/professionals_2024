package com.student.professionals2024.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertNewUser(user: UserModel)

    @Delete
    suspend fun deleteUser(user: UserModel)

    @Update
    suspend fun updateUser(user: UserModel)

    @Query("SELECT * FROM usermodel WHERE fullName LIKE :name")
    fun getUserByName(name: String): Flow<UserModel>

    @Query("SELECT * FROM usermodel WHERE supabaseUserToken == :id")
    fun getUserBySupabaseToken(id: String): Flow<UserModel>
}