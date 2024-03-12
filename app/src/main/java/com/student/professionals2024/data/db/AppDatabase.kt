package com.student.professionals2024.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ UserModel::class ], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}