package com.nortic.posdash.es.users.service;

import com.nortic.posdash.es.users.repository.UserRepository;
import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.es.users.utils.Mapper;
import com.nortic.posdash.es.users.utils.PasswordUtils;
import com.nortic.posdash.us.models.dto.UserDTO;
import com.nortic.posdash.us.models.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final PasswordUtils passwordUtils;
    private final Mapper mapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordUtils passwordUtils, Mapper mapper, UserRepository userRepository) {
        this.passwordUtils = passwordUtils;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) throws CustomException {
        log.info("Start addUser Service");

        checkIfEmailExists(userDTO.getEmail());
        checkIfUserNameExists(userDTO.getUserName());

        // Encrypt password
        userDTO.setPassword(passwordUtils.encryptPassword(userDTO.getPassword()));

        // Convertir a entidad y guardar en la base de datos
        UserEntity userEntity = mapper.convertToEntity(userDTO, UserEntity.class);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        log.info("End addUser Service");

        // Convertir la entidad guardada de nuevo a DTO y devolverlo
        return mapper.convertToDto(savedUserEntity, UserDTO.class);
    }

    @Override
    public void deleteById(UUID id) throws CustomException {
        log.info("Start deleteById Service for user id: {}", id);
        Optional<UserEntity> searchedUserId = userRepository.findById(id);
        if(searchedUserId.isPresent()) {
            UserEntity userEntity = searchedUserId.get();
            userEntity.setStatus(false);
            userEntity.markAsDeleted();
            userRepository.save(userEntity);
            log.info("User with id: {} marked as deleted", id);
        }else{
            log.error("User with id: {} not found", id);
            throw new CustomException("User not found");
        };
        log.info("End deleteById Service");
    }

    private void checkIfEmailExists(String email) throws CustomException {
        Optional<UserEntity> searchedEmail = userRepository.findByEmail(email);
        if (searchedEmail.isPresent()) {
            throw new CustomException("Email address already exists");
        }
    }

    private void checkIfUserNameExists(String userName) throws CustomException {
        Optional<UserEntity> searchedUserName = userRepository.findByUserName(userName);
        if (searchedUserName.isPresent()) {
            throw new CustomException("Username already exists");
        }
    }

}
