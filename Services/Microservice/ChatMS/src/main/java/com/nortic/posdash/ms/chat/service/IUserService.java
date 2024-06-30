package com.nortic.posdash.ms.chat.service;

import com.nortic.posdash.models.entity.UserEntity;

import java.util.UUID;

public interface IUserService {
    UserEntity findById(UUID id);
}
