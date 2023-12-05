package com.example.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "chat_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatLogEntry {
    @Id
    private String messageId;
    private String user;
    private String message;
    private Long timestamp;
    private Boolean isSent;
}
