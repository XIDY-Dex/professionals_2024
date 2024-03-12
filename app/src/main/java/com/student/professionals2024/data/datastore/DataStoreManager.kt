package com.student.professionals2024.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val dataStore = context.dataStore

    companion object {
        val onboardScreenComplete = booleanPreferencesKey("onBoardScreenComplete")
        val userAuthorizationToken = stringPreferencesKey("UserAuthorizationToken")
    }

    suspend fun setOnboardScreenComplete(result: Boolean) {
        dataStore.edit {settings ->
            settings[onboardScreenComplete] = result
        }
    }

    fun getOnboardScreenCompletion(): Flow<Boolean> {
        return dataStore.data.map {prefs ->
            prefs[onboardScreenComplete] == true
        }
    }


    suspend fun saveUserAuthorizationToken(token: String) {
        dataStore.edit { setttings ->
            setttings[userAuthorizationToken] = token
        }
    }
}