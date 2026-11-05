package com.nortic.pos.es.user.service.contract;

import com.nortic.pos.es.user.model.dto.AuthenticateRequestDTO;
import com.nortic.pos.es.user.model.dto.ChangeEmailRequestDTO;
import com.nortic.pos.es.user.model.dto.ChangePasswordRequestDTO;
import com.nortic.pos.es.user.model.dto.ChangePhoneRequestDTO;
import com.nortic.pos.es.user.model.dto.ChangeUserStatusRequestDTO;
import com.nortic.pos.es.user.model.dto.ChangeUsernameRequestDTO;
import com.nortic.pos.es.user.model.dto.PaginatedUserResponseDTO;
import com.nortic.pos.es.user.model.dto.RequestContextDTO;
import com.nortic.pos.es.user.model.dto.SearchParamsDTO;
import com.nortic.pos.es.user.model.dto.UserDTO;
import com.nortic.pos.es.user.model.dto.UserQueryRequestDTO;
import com.nortic.pos.es.user.model.dto.UserSearchRequestDTO;

import java.util.UUID;

public interface UserService {

    UserDTO changeEmail(RequestContextDTO requestContextDTO, UUID id, ChangeEmailRequestDTO changeEmailRequestDTO);

    UserDTO changePhone(RequestContextDTO requestContextDTO, UUID id, ChangePhoneRequestDTO changePhoneRequestDTO);

    UserDTO changeUserPassword(RequestContextDTO requestContextDTO, UUID id, ChangePasswordRequestDTO changePasswordRequestDTO);

    UserDTO changeUserStatus(RequestContextDTO requestContextDTO, UUID id, ChangeUserStatusRequestDTO changeStatusRequestDTO);

    UserDTO changeUsername(RequestContextDTO requestContextDTO, UUID id, ChangeUsernameRequestDTO changeUsernameRequestDTO);

    UserDTO authenticate(RequestContextDTO requestContextDTO, AuthenticateRequestDTO authenticateRequestDTO);

    UserDTO createUser(RequestContextDTO requestContextDTO, UserDTO userDTO);

    void deleteUser(RequestContextDTO requestContextDTO, UUID id);

    PaginatedUserResponseDTO getAllUsers(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO);

    UserDTO getUserById(RequestContextDTO requestContextDTO, UUID id);

    PaginatedUserResponseDTO queryUsers(RequestContextDTO requestContextDTO, UserQueryRequestDTO userQueryRequestDTO);

    UserDTO restoreUser(RequestContextDTO requestContextDTO, UUID id);

    PaginatedUserResponseDTO searchUsers(RequestContextDTO requestContextDTO, UserSearchRequestDTO userSearchRequestDTO);

    UserDTO updateUser(RequestContextDTO requestContextDTO, UUID id, UserDTO userDTO);
}
