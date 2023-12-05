package com.example.assignment.service;

import com.example.assignment.entity.ChatLogEntry;

import java.util.List;

public interface ChatLogService {
    String createChatLogEntry(ChatLogEntry chatLogEntry);
    List<ChatLogEntry> getAllChatLogOfUser(String user, int limit, String start);
    void deleteUserChatLog(String user);
    void deleteChatLogByMessageId(String user, String messageId);
}
