package com.student.professionals2024.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.student.professionals2024.domain.viewmodels.ChatViewModel
import com.student.professionals2024.ui.components.ChatListElement

@Composable
fun ChatListScreen(chatViewModel: ChatViewModel = hiltViewModel()) {
    val chatList by chatViewModel.chatList.collectAsState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp)) {
        LazyColumn {
            items(chatList.size) { index ->
                ChatListElement(userName = chatList[index].user_name, lastMessageText = chatList[index].last_message, newMessagesCount = 1)
            }
        }
    }
}