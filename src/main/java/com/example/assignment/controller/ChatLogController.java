package com.example.assignment.controller;

import com.example.assignment.entity.ChatLogEntry;
import com.example.assignment.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chatlogs")
public class ChatLogController {
    @Autowired
    private ChatLogService chatLogService;
    @PostMapping("/{user}")
    public ResponseEntity<String> createChatLog(@PathVariable String user, @RequestBody ChatLogEntry chatLogEntry){
        chatLogEntry.setUser(user);
        return new ResponseEntity<>(chatLogService.createChatLogEntry(chatLogEntry), HttpStatus.CREATED);
    }
    @DeleteMapping("{user}")
    public ResponseEntity<String> deleteUserChatLog(@PathVariable String user){
        chatLogService.deleteUserChatLog(user);
        return new ResponseEntity<>("All ChatLog delete for user: "+user, HttpStatus.OK);
    }
    @DeleteMapping("{user}/{messageId}")
    public ResponseEntity<String> deleteChatLogByMessageId(@PathVariable String user, String messageId){
        chatLogService.deleteChatLogByMessageId(user, messageId);
        return new ResponseEntity<>("chatlog delete for user: "+user+" of messageId: "+messageId, HttpStatus.OK);
    }
    @GetMapping("{user}")
    public ResponseEntity<List<ChatLogEntry>> getAllChatLogForUser(@PathVariable String user, @RequestParam(defaultValue = "10") int limit,
                                                                   @RequestParam(required = false) String start){
        List<ChatLogEntry> chatLogEntries=chatLogService.getAllChatLogOfUser(user, limit, start);
        return new ResponseEntity<>(chatLogEntries, HttpStatus.OK);
    }
}
