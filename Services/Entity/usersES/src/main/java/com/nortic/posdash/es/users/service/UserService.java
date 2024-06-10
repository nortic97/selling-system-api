package com.nortic.posdash.es.users.service;

import com.nortic.posdash.es.users.repository.UserRepository;
import com.nortic.posdash.es.users.utils.Mapper;
import com.nortic.posdash.es.users.utils.PasswordUtils;
import com.nortic.posdash.us.models.dto.UserDTO;
import com.nortic.posdash.us.models.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    Mapper mapper = new Mapper();
    PasswordUtils passwordUtils = new PasswordUtils();

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        log.info("Start addUser Service");

        // Encrypt password
        userDTO.setPassword(passwordUtils.encryptPassword(userDTO.getPassword()));

        // Convertir a entidad y guardar en la base de datos
        UserEntity userEntity = mapper.convertToEntity(userDTO, UserEntity.class);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        log.info("End addUser Service");

        // Convertir la entidad guardada de nuevo a DTO y devolverlo
        return mapper.convertToDto(savedUserEntity, UserDTO.class);
    }

}
