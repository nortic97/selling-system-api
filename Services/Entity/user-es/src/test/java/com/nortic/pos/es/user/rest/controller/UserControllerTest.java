package com.nortic.pos.es.user.rest.controller;

import com.nortic.pos.es.user.model.RestModelChangeUserStatusRequest;
import com.nortic.pos.es.user.model.RestModelPaginatedUsers;
import com.nortic.pos.es.user.model.RestModelUser;
import com.nortic.pos.es.user.service.UserSvcImpl;
import com.nortic.pos.es.user.util.RestMapper;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private RestMapper mapper;

    @Mock
    private UserSvcImpl userSvc;

    @InjectMocks
    private UserController userController;

    private UserDTO testUserDTO;
    private RestModelUser testRestModelUser;
    private RestModelPaginatedUsers testPaginatedResponse;
    private final UUID testUserId = UUID.fromString("11111111-1111-1111-1111-111111111111");

    @BeforeEach
    void setUp() {

        testUserDTO = UserDTO.builder()
                .id(testUserId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .phone("+1234567890")
                .userName("johndoe")
                .storeId(UUID.randomUUID())
                .branchId(UUID.randomUUID())
                .roleId(UUID.randomUUID())
                .accountType(AccountTypeEnum.EMPLOYEE)
                .isActive(true)
                .isPrincipalAdmin(false)
                .address("123 Test St")
                .birthDate(LocalDate.of(1990, 1, 1))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        testRestModelUser = RestModelUser.builder()
                .id(testUserId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@test.com")
                .phone("+1234567890")
                .userName("johndoe")
                .storeId(UUID.randomUUID())
                .branchId(UUID.randomUUID())
                .roleId(UUID.randomUUID())
                .accountType("Employee")
                .isActive(true)
                .isPrincipalAdmin(false)
                .address("123 Test St")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();

        testPaginatedResponse = RestModelPaginatedUsers.builder().build();

    }

    @Test
    void createUser_ShouldReturnCreatedUser_WhenValidInput() {

        when(mapper.restModelToDTO(testRestModelUser)).thenReturn(testUserDTO);
        when(userSvc.createUser(testUserDTO)).thenReturn(testUserDTO);
        when(mapper.dtoToRestModel(testUserDTO)).thenReturn(testRestModelUser);


        ResponseEntity<RestModelUser> response = userController.createUser(testRestModelUser);


        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testRestModelUser.getId(), response.getBody().getId());
        assertEquals(testRestModelUser.getFirstName(), response.getBody().getFirstName());
        assertEquals(testRestModelUser.getEmail(), response.getBody().getEmail());

        verify(mapper).restModelToDTO(testRestModelUser);
        verify(userSvc).createUser(testUserDTO);
        verify(mapper).dtoToRestModel(testUserDTO);
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserExists() {

        when(userSvc.getUser(testUserId)).thenReturn(testUserDTO);
        when(mapper.dtoToRestModel(testUserDTO)).thenReturn(testRestModelUser);


        ResponseEntity<RestModelUser> response = userController.getUserById(testUserId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testRestModelUser.getId(), response.getBody().getId());

        verify(userSvc).getUser(testUserId);
        verify(mapper).dtoToRestModel(testUserDTO);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser_WhenValidInput() {

        when(mapper.restModelToDTO(testRestModelUser)).thenReturn(testUserDTO);
        when(userSvc.updateUser(testUserId, testUserDTO)).thenReturn(testUserDTO);
        when(mapper.dtoToRestModel(testUserDTO)).thenReturn(testRestModelUser);


        ResponseEntity<RestModelUser> response = userController.updateUser(testUserId, testRestModelUser);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testRestModelUser.getId(), response.getBody().getId());

        verify(mapper).restModelToDTO(testRestModelUser);
        verify(userSvc).updateUser(testUserId, testUserDTO);
        verify(mapper).dtoToRestModel(testUserDTO);
    }

    @Test
    void softDeleteUser_ShouldReturnOk_WhenUserExists() {

        doNothing().when(userSvc).softDeleteUser(testUserId);

        ResponseEntity<Void> response = userController.deleteUser(testUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        verify(userSvc).softDeleteUser(testUserId);
    }

    @Test
    void changeUserStatus_ShouldReturnOk_WhenValidInput() {

        RestModelChangeUserStatusRequest request = new RestModelChangeUserStatusRequest();
        request.setIsActive(false);
        doNothing().when(userSvc).changeUserStatus(testUserId, false);

        ResponseEntity<RestModelUser> response = userController.changeUserStatus(testUserId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        verify(userSvc).changeUserStatus(testUserId, false);
    }

    @Test
    void getAllUsers_ShouldReturnPaginatedUsers_WhenNoFilters() {

        List<UserDTO> userList = Collections.singletonList(testUserDTO);
        Page<UserDTO> userPage = new PageImpl<>(userList, PageRequest.of(0, 10), 1);

        when(userSvc.getAllUsers(any(Pageable.class), isNull(), isNull(), isNull(), isNull(), isNull(), isNull()))
                .thenReturn(userPage);
        when(mapper.pageToRestModelPaginatedUsers(userPage)).thenReturn(testPaginatedResponse);


        ResponseEntity<RestModelPaginatedUsers> response = userController.getAllUsers(
                0, 10, "firstName,asc", null, null, null, null, null, null);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(userSvc).getAllUsers(any(Pageable.class), isNull(), isNull(), isNull(), isNull(), isNull(), isNull());
        verify(mapper).pageToRestModelPaginatedUsers(userPage);
    }

    @Test
    void getAllUsers_ShouldReturnPaginatedUsers_WithAccountTypeFilter() {

        List<UserDTO> userList = Collections.singletonList(testUserDTO);
        Page<UserDTO> userPage = new PageImpl<>(userList, PageRequest.of(0, 10), 1);

        when(userSvc.getAllUsers(any(Pageable.class), isNull(), isNull(), isNull(), eq(AccountTypeEnum.EMPLOYEE), isNull(), isNull()))
                .thenReturn(userPage);
        when(mapper.pageToRestModelPaginatedUsers(userPage)).thenReturn(testPaginatedResponse);

        ResponseEntity<RestModelPaginatedUsers> response = userController.getAllUsers(
                0, 10, "firstName,asc", null, null, null, "Employee", null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(userSvc).getAllUsers(any(Pageable.class), isNull(), isNull(), isNull(), eq(AccountTypeEnum.EMPLOYEE), isNull(), isNull());
        verify(mapper).pageToRestModelPaginatedUsers(userPage);
    }

    @Test
    void getAllUsers_ShouldReturnPaginatedUsers_WithStoreOwnerAccountType() {

        List<UserDTO> userList = Collections.singletonList(testUserDTO);
        Page<UserDTO> userPage = new PageImpl<>(userList, PageRequest.of(0, 10), 1);

        when(userSvc.getAllUsers(any(Pageable.class), isNull(), isNull(), isNull(), eq(AccountTypeEnum.STORE_OWNER), isNull(), isNull()))
                .thenReturn(userPage);
        when(mapper.pageToRestModelPaginatedUsers(userPage)).thenReturn(testPaginatedResponse);

        ResponseEntity<RestModelPaginatedUsers> response = userController.getAllUsers(
                0, 10, "firstName,asc", null, null, null, "Store owner", null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(userSvc).getAllUsers(any(Pageable.class), isNull(), isNull(), isNull(), eq(AccountTypeEnum.STORE_OWNER), isNull(), isNull());
        verify(mapper).pageToRestModelPaginatedUsers(userPage);
    }

    @Test
    void getDeletedUsers_ShouldReturnPaginatedUsers_WhenNoFilters() {

        List<UserDTO> userList = Collections.singletonList(testUserDTO);
        Page<UserDTO> userPage = new PageImpl<>(userList, PageRequest.of(0, 10), 1);

        when(userSvc.getDeletedUsers(any(Pageable.class), isNull(), isNull(), isNull(), isNull(), isNull(), isNull()))
                .thenReturn(userPage);
        when(mapper.pageToRestModelPaginatedUsers(userPage)).thenReturn(testPaginatedResponse);


        ResponseEntity<RestModelPaginatedUsers> response = userController.getDeletedUsers(
                0, 10, "firstName,asc", null, null, null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(userSvc).getDeletedUsers(any(Pageable.class), isNull(), isNull(), isNull(), isNull(), isNull(), isNull());
        verify(mapper).pageToRestModelPaginatedUsers(userPage);
    }

    @Test
    void getDeletedUsers_ShouldReturnPaginatedUsers_WithAccountTypeFilter() {

        List<UserDTO> userList = Collections.singletonList(testUserDTO);
        Page<UserDTO> userPage = new PageImpl<>(userList, PageRequest.of(0, 10), 1);

        when(userSvc.getDeletedUsers(any(Pageable.class), isNull(), isNull(), isNull(), eq(AccountTypeEnum.INTERNAL), isNull(), isNull()))
                .thenReturn(userPage);
        when(mapper.pageToRestModelPaginatedUsers(userPage)).thenReturn(testPaginatedResponse);

        ResponseEntity<RestModelPaginatedUsers> response = userController.getDeletedUsers(
                0, 10, "firstName,asc", null, null, null, "Internal", null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(userSvc).getDeletedUsers(any(Pageable.class), isNull(), isNull(), isNull(), eq(AccountTypeEnum.INTERNAL), isNull(), isNull());
        verify(mapper).pageToRestModelPaginatedUsers(userPage);
    }

    @Test
    void deleteUser_ShouldReturnOk_WhenUserExists() {

        doNothing().when(userSvc).deleteUser(testUserId);

        ResponseEntity<Void> response = userController.permanentDeleteUser(testUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        verify(userSvc).deleteUser(testUserId);
    }
}
