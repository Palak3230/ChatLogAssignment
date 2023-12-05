package com.example.assignment.service.implementation;

import com.example.assignment.entity.ChatLogEntry;
import com.example.assignment.exception.EntryNotValidException;
import com.example.assignment.exception.NoEntryExists;
import com.example.assignment.repository.ChatLogEntryRepository;
import com.example.assignment.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatLogServiceImpl implements ChatLogService {
    @Autowired
    private ChatLogEntryRepository chatLogEntryRepository;

    @Override
    public String createChatLogEntry(ChatLogEntry chatLogEntry) {
        if (isValidChatLog(chatLogEntry)) {
            String messageId = UUID.randomUUID().toString();
            chatLogEntry.setMessageId(messageId);
            chatLogEntryRepository.save(chatLogEntry);
            return messageId;
        } else {
            throw new EntryNotValidException("Not a valid entry!! Provide details correctly!!");
        }
    }

   @Override
   public List<ChatLogEntry> getAllChatLogOfUser(String user, int limit, String start) {
        if(isValidUser(user)){
            if(limit == 0 ) {
                limit = 10;
            }
           List<ChatLogEntry> chatLogEntries;
            if(start != null){
                chatLogEntries = chatLogEntryRepository.findByUserAndMessageIdOrderByMessageIdDesc(user, start, PageRequest.of(0, limit));
            }else{
                chatLogEntries = chatLogEntryRepository.findByUserOrderByMessageIdDesc(user, PageRequest.of(0,limit));
                }
           if(chatLogEntries.isEmpty()){
                throw new NoEntryExists("No chatlog for given user");
            }
            return chatLogEntries;
}
       else{
            throw new EntryNotValidException("User is incorrect!");
   }
   }
@Override
    public void deleteUserChatLog(String user) {
        if (isValidUser(user)) {
            Long deletedLog = chatLogEntryRepository.deleteByUser(user);
            if (deletedLog == 0) {
                throw new NoEntryExists("There is no chatlog for user:" + user);
            }
        }else {
            throw new EntryNotValidException("User is incorrect!");
        }
    }
    @Override
    public void deleteChatLogByMessageId(String user, String messageId){
        if (isValidUser(user)&&messageId!=null) {
            Long deletedLog = chatLogEntryRepository.deleteByUserAndMessageId(user, messageId);
            if (deletedLog == 0) {
                throw new NoEntryExists("There is no chatlog for user:" + user);
            }
        }else {
            throw new EntryNotValidException("Enter correct user or messageId");
        }
    }
    private boolean isValidChatLog(ChatLogEntry chatLogEntry) {
        if (!isValidUser(chatLogEntry.getUser()) || chatLogEntry.getMessage() == null
                || chatLogEntry.getTimestamp() == null || chatLogEntry.getIsSent() == null) {
            return false;
        }
        return true;
    }

    private boolean isValidUser(String user) {
        return user.matches("[a-zA-Z0-9]{1,16}");
    }
}
