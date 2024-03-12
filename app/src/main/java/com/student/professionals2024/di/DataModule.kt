package com.student.professionals2024.di

import android.content.Context
import androidx.room.Room
import com.student.professionals2024.data.datastore.DataStoreManager
import com.student.professionals2024.data.db.AppDatabase
import com.student.professionals2024.data.db.UserModel
import com.student.professionals2024.domain.repository.DeliveryAppRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDeliveryAppRepository(dataStoreManager: DataStoreManager, supabaseClient: SupabaseClient, database: AppDatabase): DeliveryAppRepoImpl {
        return DeliveryAppRepoImpl(dataStoreManager, supabaseClient, database)
    }

    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "deliveryDatabase").build()
    }
}