package com.nortic.posdash.ms.chat.repository;

import com.nortic.posdash.models.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
