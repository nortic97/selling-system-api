package com.nortic.posdash.ms.chat.service;

import com.nortic.posdash.models.dto.UserDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IUserService {
    UserDTO findById(UUID id);
    List<UserDTO> findByIds(Set<UUID> userIds);
}
