package com.student.professionals2024.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.student.professionals2024.core.UserState
import com.student.professionals2024.domain.models.dto.ChatDto
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val supabaseClient: SupabaseClient): ViewModel() {
    private val _loadingState = MutableStateFlow<UserState>(UserState.Default)
    val loadingState = _loadingState.asStateFlow()

    private val _chatList = MutableStateFlow<List<ChatDto>>(emptyList())
    val chatList = _chatList.asStateFlow()

    init {
        refreshChatList()
        listenToChatList()
    }

    private fun listenToChatList() {
        Log.d("CHATS", "Connecting to Realtime")
        viewModelScope.launch {
            try {
                val channel = supabaseClient.realtime.channel("chatList")
                supabaseClient.realtime.connect()

                Log.d("CHATS", "Realtime connected Successful")

                val dataFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public")
                channel.subscribe()
                dataFlow.collect {
                    when (it) {
                        is PostgresAction.Delete -> {
                            refreshChatList()
                        }

                        is PostgresAction.Insert -> {
                            refreshChatList()
                        }

                        is PostgresAction.Select -> {}
                        is PostgresAction.Update -> {
                            refreshChatList()
                        }
                    }
                }
            }
            catch(e: Exception) {
                Log.d("CHATS", e.message!!)
            }
        }
    }

    private fun refreshChatList() {
        viewModelScope.launch {
            try {
                _loadingState.value = UserState.Loading
                _chatList.value = supabaseClient.postgrest.from("chats").select().decodeList()
                _loadingState.value = UserState.Success("")
                Log.d("CHATS", "Chats sucessfully update")
            }
            catch(e: Exception) {
                Log.d("REALTIME", e.message!!)
            }
        }
    }


}