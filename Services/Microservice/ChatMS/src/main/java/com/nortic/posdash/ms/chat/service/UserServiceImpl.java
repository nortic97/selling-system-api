package com.nortic.posdash.ms.chat.service;

import com.nortic.posdash.models.dto.UserDTO;
import com.nortic.posdash.models.entity.UserEntity;
import com.nortic.posdash.ms.chat.repository.UserRepository;
import com.nortic.posdash.ms.chat.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO findById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return mapper.convertToDto(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findByIds(Set<UUID> userIds) {
        List<UserEntity> users = userRepository.findAllByIdIn(userIds);
        return mapper.convertToDtoList(users, UserDTO.class);
    }
}
