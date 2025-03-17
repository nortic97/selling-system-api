package com.nortic.posdash.us.auth.service;

import com.nortic.posdash.models.dto.AuthRequestDTO;
import com.nortic.posdash.models.dto.TokenDTO;
import com.nortic.posdash.us.auth.utils.CustomException;

import java.util.UUID;

public interface IAuthService {
    TokenDTO generateToken(AuthRequestDTO authRequest) throws CustomException;
    boolean hasPermission(UUID roleId, String method, String url) throws CustomException;
}
