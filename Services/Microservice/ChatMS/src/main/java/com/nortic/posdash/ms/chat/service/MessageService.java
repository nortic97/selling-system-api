package com.nortic.posdash.ms.chat.service;

import com.nortic.posdash.models.dto.MessageDTO;
import com.nortic.posdash.models.entity.MessageEntity;
import com.nortic.posdash.ms.chat.repository.MessageRepository;
import com.nortic.posdash.ms.chat.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final Mapper mapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, Mapper mapper) {
        this.messageRepository = messageRepository;
        this.mapper = mapper;
    }

    public void sendMessage(MessageDTO messageDTO) {
        MessageEntity message = mapper.convertToEntity(messageDTO, MessageEntity.class);
        messageRepository.save(message);
    }

}
