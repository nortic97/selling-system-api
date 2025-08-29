package com.nortic.pos.es.user.grpc;

import com.nortic.pos.es.user.grpc.gen.*;
import com.nortic.pos.es.user.service.IUserSvc;
import com.nortic.pos.es.user.util.GrpcErrorHandler;
import com.nortic.pos.es.user.util.GrpcMapper;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import com.nortic.pos.models.enums.DocumentTypeEnum;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGrpcSvcImplTest {

    @Mock
    private IUserSvc userService;

    @Mock
    private GrpcMapper grpcMapper;

    @Mock
    private GrpcErrorHandler grpcErrorHandler;

    @Mock
    private StreamObserver<User> userResponseObserver;

    @Mock
    private StreamObserver<DeleteResponse> deleteResponseObserver;

    @Mock
    private StreamObserver<UserResponse> userResponseObserver2;

    @Mock
    private StreamObserver<UserListResponse> userListResponseObserver;

    @InjectMocks
    private UserGrpcSvcImpl userGrpcService;

    private UserDTO testUserDTO;
    private User testProtoUser;
    private UUID testUserId;

    @BeforeEach
    void setUp() {
        testUserId = UUID.randomUUID();
        
        testUserDTO = UserDTO.builder()
                .id(testUserId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .userName("johndoe")
                .password("password123")
                .documentType(DocumentTypeEnum.CC)
                .documentNumber("12345678")
                .phone("+1234567890")
                .address("123 Main St")
                .storeId(UUID.randomUUID())
                .branchId(UUID.randomUUID())
                .roleId(UUID.randomUUID())
                .accountType(AccountTypeEnum.EMPLOYEE)
                .isActive(true)
                .isPrincipalAdmin(false)
                .birthDate(LocalDate.of(1990, 1, 15))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        testProtoUser = User.newBuilder()
                .setId(testUserId.toString())
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .setUserName("johndoe")
                .setPassword("password123")
                .setDocumentType(DocumentType.CC)
                .setDocumentNumber("12345678")
                .setPhone("+1234567890")
                .setAddress("123 Main St")
                .setStoreId(UUID.randomUUID().toString())
                .setBranchId(UUID.randomUUID().toString())
                .setRoleId(UUID.randomUUID().toString())
                .setAccountType(AccountType.EMPLOYEE)
                .setIsActive(true)
                .setIsPrincipalAdmin(false)
                .setBirthDate("1990-01-15")
                .build();
    }

    @Test
    void createUser_Success() {
        // Given
        when(grpcMapper.protoUserToDTO(testProtoUser)).thenReturn(testUserDTO);
        when(userService.createUser(testUserDTO)).thenReturn(testUserDTO);
        when(grpcMapper.dtoToProtoUser(testUserDTO)).thenReturn(testProtoUser);

        // When
        userGrpcService.createUser(testProtoUser, userResponseObserver);

        // Then
        verify(grpcMapper).protoUserToDTO(testProtoUser);
        verify(userService).createUser(testUserDTO);
        verify(grpcMapper).dtoToProtoUser(testUserDTO);
        verify(userResponseObserver).onNext(testProtoUser);
        verify(userResponseObserver).onCompleted();
        verify(grpcErrorHandler, never()).handleError(any(), any());
    }

    @Test
    void createUser_Exception() {
        // Given
        RuntimeException exception = new RuntimeException("Test exception");
        when(grpcMapper.protoUserToDTO(testProtoUser)).thenReturn(testUserDTO);
        when(userService.createUser(testUserDTO)).thenThrow(exception);

        // When
        userGrpcService.createUser(testProtoUser, userResponseObserver);

        // Then
        verify(grpcErrorHandler).handleError(exception, userResponseObserver);
        verify(userResponseObserver, never()).onNext(any());
        verify(userResponseObserver, never()).onCompleted();
    }

    @Test
    void getUserById_Success() {
        // Given
        UserRequest request = UserRequest.newBuilder()
                .setId(testUserId.toString())
                .build();
        
        when(userService.getUser(testUserId)).thenReturn(testUserDTO);
        when(grpcMapper.dtoToProtoUser(testUserDTO)).thenReturn(testProtoUser);

        // When
        userGrpcService.getUserById(request, userResponseObserver);

        // Then
        verify(userService).getUser(testUserId);
        verify(grpcMapper).dtoToProtoUser(testUserDTO);
        verify(userResponseObserver).onNext(testProtoUser);
        verify(userResponseObserver).onCompleted();
    }

    @Test
    void updateUser_Success() {
        // Given
        when(grpcMapper.protoUserToDTO(testProtoUser)).thenReturn(testUserDTO);
        when(userService.updateUser(testUserId, testUserDTO)).thenReturn(testUserDTO);
        when(grpcMapper.dtoToProtoUser(testUserDTO)).thenReturn(testProtoUser);

        // When
        userGrpcService.updateUser(testProtoUser, userResponseObserver);

        // Then
        verify(grpcMapper).protoUserToDTO(testProtoUser);
        verify(userService).updateUser(testUserId, testUserDTO);
        verify(grpcMapper).dtoToProtoUser(testUserDTO);
        verify(userResponseObserver).onNext(testProtoUser);
        verify(userResponseObserver).onCompleted();
    }

    @Test
    void deleteUser_Success() {
        // Given
        UserRequest request = UserRequest.newBuilder()
                .setId(testUserId.toString())
                .build();

        // When
        userGrpcService.deleteUser(request, deleteResponseObserver);

        // Then
        verify(userService).softDeleteUser(testUserId);
        
        ArgumentCaptor<DeleteResponse> responseCaptor = ArgumentCaptor.forClass(DeleteResponse.class);
        verify(deleteResponseObserver).onNext(responseCaptor.capture());
        verify(deleteResponseObserver).onCompleted();
        
        DeleteResponse response = responseCaptor.getValue();
        assertTrue(response.getSuccess());
    }

    @Test
    void softDeleteUser_Success() {
        // Given
        UserRequest request = UserRequest.newBuilder()
                .setId(testUserId.toString())
                .build();

        // When
        userGrpcService.softDeleteUser(request, deleteResponseObserver);

        // Then
        verify(userService).softDeleteUser(testUserId);
        
        ArgumentCaptor<DeleteResponse> responseCaptor = ArgumentCaptor.forClass(DeleteResponse.class);
        verify(deleteResponseObserver).onNext(responseCaptor.capture());
        verify(deleteResponseObserver).onCompleted();
        
        DeleteResponse response = responseCaptor.getValue();
        assertTrue(response.getSuccess());
    }

    @Test
    void permanentDeleteUser_Success() {
        // Given
        UserRequest request = UserRequest.newBuilder()
                .setId(testUserId.toString())
                .build();

        // When
        userGrpcService.permanentDeleteUser(request, deleteResponseObserver);

        // Then
        verify(userService).deleteUser(testUserId);
        
        ArgumentCaptor<DeleteResponse> responseCaptor = ArgumentCaptor.forClass(DeleteResponse.class);
        verify(deleteResponseObserver).onNext(responseCaptor.capture());
        verify(deleteResponseObserver).onCompleted();
        
        DeleteResponse response = responseCaptor.getValue();
        assertTrue(response.getSuccess());
    }

    @Test
    void changeUserStatus_Success() {
        // Given
        ChangeStatusRequest request = ChangeStatusRequest.newBuilder()
                .setId(testUserId.toString())
                .setStatus(true)
                .build();

        // When
        userGrpcService.changeUserStatus(request, userResponseObserver2);

        // Then
        verify(userService).changeUserStatus(testUserId, true);
        
        ArgumentCaptor<UserResponse> responseCaptor = ArgumentCaptor.forClass(UserResponse.class);
        verify(userResponseObserver2).onNext(responseCaptor.capture());
        verify(userResponseObserver2).onCompleted();
        
        UserResponse response = responseCaptor.getValue();
        assertTrue(response.getSuccess());
    }

    @Test
    void getAllUsers_Success() {
        // Given
        GetUsersRequest request = GetUsersRequest.newBuilder()
                .setPage(1)
                .setSize(10)
                .setSort("firstName,asc")
                .setStoreId(UUID.randomUUID().toString())
                .setSearch("john")
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        Page<UserDTO> userPage = new PageImpl<>(List.of(testUserDTO), pageable, 1);
        UserListResponse expectedResponse = UserListResponse.newBuilder()
                .addUsers(testProtoUser)
                .setTotalElements(1)
                .setTotalPages(1)
                .setCurrentPage(0)
                .setPageSize(10)
                .setHasNext(false)
                .setHasPrevious(false)
                .build();

        when(grpcMapper.getUsersRequestToPageable(request)).thenReturn(pageable);
        when(userService.getAllUsers(eq(pageable), any(), any(), any(), any(), any(), eq("john")))
                .thenReturn(userPage);
        when(grpcMapper.pageToUserListResponse(userPage)).thenReturn(expectedResponse);

        // When
        userGrpcService.getAllUsers(request, userListResponseObserver);

        // Then
        verify(grpcMapper).getUsersRequestToPageable(request);
        verify(userService).getAllUsers(eq(pageable), any(), any(), any(), any(), any(), eq("john"));
        verify(grpcMapper).pageToUserListResponse(userPage);
        verify(userListResponseObserver).onNext(expectedResponse);
        verify(userListResponseObserver).onCompleted();
    }

    @Test
    void getDeletedUsers_Success() {
        // Given
        GetUsersRequest request = GetUsersRequest.newBuilder()
                .setPage(0)
                .setSize(10)
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        Page<UserDTO> deletedUserPage = new PageImpl<>(List.of(testUserDTO), pageable, 1);
        UserListResponse expectedResponse = UserListResponse.newBuilder()
                .addUsers(testProtoUser)
                .setTotalElements(1)
                .setTotalPages(1)
                .setCurrentPage(0)
                .setPageSize(10)
                .setHasNext(false)
                .setHasPrevious(false)
                .build();

        when(grpcMapper.getUsersRequestToPageable(request)).thenReturn(pageable);
        when(userService.getDeletedUsers(eq(pageable), any(), any(), any(), any(), any(), any()))
                .thenReturn(deletedUserPage);
        when(grpcMapper.pageToUserListResponse(deletedUserPage)).thenReturn(expectedResponse);

        // When
        userGrpcService.getDeletedUsers(request, userListResponseObserver);

        // Then
        verify(grpcMapper).getUsersRequestToPageable(request);
        verify(userService).getDeletedUsers(eq(pageable), any(), any(), any(), any(), any(), any());
        verify(grpcMapper).pageToUserListResponse(deletedUserPage);
        verify(userListResponseObserver).onNext(expectedResponse);
        verify(userListResponseObserver).onCompleted();
    }
}
