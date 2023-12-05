package com.example.assignment.repository;

import com.example.assignment.entity.ChatLogEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ChatLogEntryRepository extends JpaRepository<ChatLogEntry, String> {
    @Transactional
    long deleteByUser(String user);
    long deleteByUserAndMessageId(String user, String messageId);
    List<ChatLogEntry> findByUserAndMessageIdOrderByMessageIdDesc(String user, String messageId, Pageable pageable);
    List<ChatLogEntry> findByUserOrderByMessageIdDesc(String messageId, Pageable pageable);
}
