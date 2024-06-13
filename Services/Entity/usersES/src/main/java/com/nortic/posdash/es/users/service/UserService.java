package com.nortic.posdash.es.users.service;

import com.nortic.posdash.es.users.repository.UserRepository;
import com.nortic.posdash.es.users.utils.CustomException;
import com.nortic.posdash.es.users.utils.Mapper;
import com.nortic.posdash.es.users.utils.PasswordUtils;
import com.nortic.posdash.us.models.dto.UserDTO;
import com.nortic.posdash.us.models.entity.RoleEntity;
import com.nortic.posdash.us.models.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public UserDTO getUserById(UUID id) throws CustomException {
        log.info("Start getUserById Service");
        UserEntity userEntity = checkIfIdExists(id);
        log.info("End getUserById Service");
        return mapper.convertToDto(userEntity, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() throws CustomException {
        log.info("Start getAllUsers Service");
        List<UserEntity> userEntityList = userRepository.findAll(Sort.by("fullName").ascending());
        log.info("End getAllUsers Service");
        return mapper.convertToDtoList(userEntityList, UserDTO.class);
    }

    @Override
    public Page<UserDTO> getAllUsers(int page, int size) throws CustomException {
        log.info("Start getAllUsers Service");
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending());
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        log.info("End getAllUsers Service");
        return mapper.convertToDtoPage(userEntityPage, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO user) throws CustomException {
        log.info("Start updateUser Service");
        UserEntity userEntity = checkIfIdExists(id);

        userEntity.setFullName(user.getFullName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setStatus(user.isStatus());
        userEntity.setAddress(user.getAddress());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setPhoto(user.getPhoto());

        if (user.getPassword() != null && !passwordUtils.verifyPassword(user.getPassword(), userEntity.getPassword())) {
            log.info("Password Changed");
            userEntity.setPassword(passwordUtils.encryptPassword(user.getPassword()));
        }

        if (user.getRole() != null && !user.getRole().equals(userEntity.getRole().getId().toString())) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(user.getRole().getId());
            userEntity.setRole(roleEntity);
        }

        userEntity = userRepository.save(userEntity);
        log.info("End updateUser Service");
        return mapper.convertToDto(userEntity, UserDTO.class);
    }

    @Override
    public void deleteById(UUID id) throws CustomException {
        log.info("Start deleteById Service for user id: {}", id);
        UserEntity userEntity = checkIfIdExists(id);
        userEntity.setStatus(false);
        userEntity.markAsDeleted();
        userRepository.save(userEntity);
        log.info("User with id: {} marked as deleted", id);
        log.info("End deleteById Service");
    }

    private UserEntity checkIfIdExists(UUID id) throws CustomException {
        Optional<UserEntity> searchedId = userRepository.findById(id);
        if (searchedId.isEmpty()) {
            throw new CustomException("User not found");
        }
        return searchedId.get();
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
