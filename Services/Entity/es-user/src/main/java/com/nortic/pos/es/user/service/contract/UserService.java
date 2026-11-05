package com.nortic.pos.es.user.service.contract;

import com.nortic.pos.es.user.model.dto.*;

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
