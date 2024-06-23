package com.nortic.posdash.us.auth.service;

import com.nortic.posdash.us.auth.config.JwtUtil;
import com.nortic.posdash.us.auth.repository.RoleRepository;
import com.nortic.posdash.us.auth.repository.UserRepository;
import com.nortic.posdash.us.auth.utils.CustomException;
import com.nortic.posdash.us.auth.utils.PasswordUtils;
import com.nortic.posdash.us.models.dto.AuthRequestDTO;
import com.nortic.posdash.us.models.dto.TokenDTO;
import com.nortic.posdash.us.models.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements IAuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordUtils passwordUtils;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthService(JwtUtil jwtUtil, UserRepository userRepository, PasswordUtils passwordUtils, RoleRepository roleRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
        this.roleRepository = roleRepository;
    }

    @Override
    public TokenDTO generateToken(AuthRequestDTO authRequest) throws CustomException {
        log.info("Start generateToken Service");
        UserEntity userEntity = checkIfUserNameExists(authRequest.getUsername());

        if (!passwordUtils.verifyPassword(authRequest.getPassword(), userEntity.getPassword())) {
            throw new CustomException("Incorrect password");
        }

        Map<String, Object> additionalClaims = getAdditionalUserDetails(userEntity);

        final String accessToken = jwtUtil.generateToken(userEntity.getId().toString(), additionalClaims);
        final String refreshToken = jwtUtil.generateRefreshToken(userEntity.getId().toString());

        TokenDTO tokens = new TokenDTO();
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);
        tokens.setTokenType("Bearer");

        log.info("End generateToken Service");
        return tokens;
    }

    @Override
    public boolean hasPermission(UUID roleId, String method, String url) throws CustomException {
        log.info("Start hasPermission Service");
        log.info("Start hasPermission Service");
        return roleRepository.hasPermission(roleId, method, url);
    }

    private Map<String, Object> getAdditionalUserDetails(UserEntity userEntity) {
        log.info("Start getAdditionalUserDetails Service");
        Map<String, Object> additionalClaims = new LinkedHashMap<>();
        additionalClaims.put("username", userEntity.getUserName());
        additionalClaims.put("role", userEntity.getRole().getName());
        additionalClaims.put("email", userEntity.getEmail());
        additionalClaims.put("pic", userEntity.getPhoto());
        additionalClaims.put("fullName", userEntity.getFullName());
        log.info("End getAdditionalUserDetails Service");

        return additionalClaims;
    }

    private UserEntity checkIfUserNameExists(String userName) throws CustomException {
        Optional<UserEntity> searchedUserName = userRepository.findByUserName(userName);
        if (searchedUserName.isEmpty()) {
            log.error("User name {} not found", userName);
            throw new CustomException("User does not exist");
        }
        return searchedUserName.get();
    }

}
