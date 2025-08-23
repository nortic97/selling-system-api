package com.nortic.pos.es.users.grpc;

import com.nortic.pos.es.users.grpc.gen.*;
import com.nortic.pos.es.users.service.IUserSvc;
import com.nortic.pos.es.users.util.GrpcErrorHandler;
import com.nortic.pos.es.users.util.GrpcMapper;
import com.nortic.pos.models.dto.UserDTO;
import com.nortic.pos.models.enums.AccountTypeEnum;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class UserGrpcSvcImpl extends UserServiceGrpc.UserServiceImplBase {

    private final IUserSvc userService;
    private final GrpcMapper grpcMapper;
    private final GrpcErrorHandler grpcErrorHandler;

    @Override
    public void createUser(User request, StreamObserver<User> responseObserver) {
        try {
            log.info("gRPC CreateUser called for user: {}", request.getEmail());
            
            UserDTO userDTO = grpcMapper.protoUserToDTO(request);
            UserDTO createdUser = userService.createUser(userDTO);
            User response = grpcMapper.dtoToProtoUser(createdUser);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC CreateUser completed successfully for user: {}", createdUser.getEmail());
        } catch (Exception e) {
            log.error("Error in gRPC CreateUser: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void getUserById(UserRequest request, StreamObserver<User> responseObserver) {
        try {
            log.info("gRPC GetUserById called for ID: {}", request.getId());
            
            UUID userId = UUID.fromString(request.getId());
            UserDTO userDTO = userService.getUser(userId);
            User response = grpcMapper.dtoToProtoUser(userDTO);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC GetUserById completed successfully for ID: {}", request.getId());
        } catch (Exception e) {
            log.error("Error in gRPC GetUserById: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void updateUser(User request, StreamObserver<User> responseObserver) {
        try {
            log.info("gRPC UpdateUser called for user ID: {}", request.getId());
            
            UUID userId = UUID.fromString(request.getId());
            UserDTO userDTO = grpcMapper.protoUserToDTO(request);
            UserDTO updatedUser = userService.updateUser(userId, userDTO);
            User response = grpcMapper.dtoToProtoUser(updatedUser);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC UpdateUser completed successfully for user ID: {}", request.getId());
        } catch (Exception e) {
            log.error("Error in gRPC UpdateUser: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void deleteUser(UserRequest request, StreamObserver<DeleteResponse> responseObserver) {
        try {
            log.info("gRPC DeleteUser (soft) called for ID: {}", request.getId());
            
            UUID userId = UUID.fromString(request.getId());
            userService.softDeleteUser(userId);
            
            DeleteResponse response = DeleteResponse.newBuilder()
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC DeleteUser (soft) completed successfully for ID: {}", request.getId());
        } catch (Exception e) {
            log.error("Error in gRPC DeleteUser: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void softDeleteUser(UserRequest request, StreamObserver<DeleteResponse> responseObserver) {
        try {
            log.info("gRPC SoftDeleteUser called for ID: {}", request.getId());
            
            UUID userId = UUID.fromString(request.getId());
            userService.softDeleteUser(userId);
            
            DeleteResponse response = DeleteResponse.newBuilder()
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC SoftDeleteUser completed successfully for ID: {}", request.getId());
        } catch (Exception e) {
            log.error("Error in gRPC SoftDeleteUser: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void permanentDeleteUser(UserRequest request, StreamObserver<DeleteResponse> responseObserver) {
        try {
            log.info("gRPC PermanentDeleteUser called for ID: {}", request.getId());
            
            UUID userId = UUID.fromString(request.getId());
            userService.deleteUser(userId);
            
            DeleteResponse response = DeleteResponse.newBuilder()
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC PermanentDeleteUser completed successfully for ID: {}", request.getId());
        } catch (Exception e) {
            log.error("Error in gRPC PermanentDeleteUser: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void changeUserStatus(ChangeStatusRequest request, StreamObserver<UserResponse> responseObserver) {
        try {
            log.info("gRPC ChangeUserStatus called for ID: {} with status: {}", 
                    request.getId(), request.getStatus());
            
            UUID userId = UUID.fromString(request.getId());
            userService.changeUserStatus(userId, request.getStatus());
            
            UserResponse response = UserResponse.newBuilder()
                    .setSuccess(true)
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC ChangeUserStatus completed successfully for ID: {}", request.getId());
        } catch (Exception e) {
            log.error("Error in gRPC ChangeUserStatus: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void getAllUsers(GetUsersRequest request, StreamObserver<UserListResponse> responseObserver) {
        try {
            log.info("gRPC GetAllUsers called with filters - storeId: {}, branchId: {}, search: '{}'", 
                    request.hasStoreId() ? request.getStoreId() : "null",
                    request.hasBranchId() ? request.getBranchId() : "null",
                    request.hasSearch() ? request.getSearch() : "");

            // Convert proto request to service parameters
            Pageable pageable = grpcMapper.getUsersRequestToPageable(request);
            UUID storeId = request.hasStoreId() && !request.getStoreId().isEmpty() ? 
                    UUID.fromString(request.getStoreId()) : null;
            UUID branchId = request.hasBranchId() && !request.getBranchId().isEmpty() ? 
                    UUID.fromString(request.getBranchId()) : null;
            UUID roleId = request.hasRoleId() && !request.getRoleId().isEmpty() ? 
                    UUID.fromString(request.getRoleId()) : null;
            AccountTypeEnum accountType = request.hasAccountType() ? 
                    grpcMapper.protoAccountTypeToEnum(request.getAccountType()) : null;
            Boolean isActive = request.hasIsActive() ? request.getIsActive() : null;
            String search = request.hasSearch() && !request.getSearch().isEmpty() ? 
                    request.getSearch() : null;
            
            // Call service
            Page<UserDTO> usersPage = userService.getAllUsers(
                    pageable, storeId, branchId, roleId, accountType, isActive, search);
            
            // Convert response
            UserListResponse response = grpcMapper.pageToUserListResponse(usersPage);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC GetAllUsers completed successfully. Found {} users in {} pages", 
                    usersPage.getTotalElements(), usersPage.getTotalPages());
        } catch (Exception e) {
            log.error("Error in gRPC GetAllUsers: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }

    @Override
    public void getDeletedUsers(GetUsersRequest request, StreamObserver<UserListResponse> responseObserver) {
        try {
            log.info("gRPC GetDeletedUsers called with filters - storeId: {}, branchId: {}, search: '{}'", 
                    request.hasStoreId() ? request.getStoreId() : "null",
                    request.hasBranchId() ? request.getBranchId() : "null",
                    request.hasSearch() ? request.getSearch() : "");

            // Convert proto request to service parameters
            Pageable pageable = grpcMapper.getUsersRequestToPageable(request);
            UUID storeId = request.hasStoreId() && !request.getStoreId().isEmpty() ? 
                    UUID.fromString(request.getStoreId()) : null;
            UUID branchId = request.hasBranchId() && !request.getBranchId().isEmpty() ? 
                    UUID.fromString(request.getBranchId()) : null;
            UUID roleId = request.hasRoleId() && !request.getRoleId().isEmpty() ? 
                    UUID.fromString(request.getRoleId()) : null;
            AccountTypeEnum accountType = request.hasAccountType() ? 
                    grpcMapper.protoAccountTypeToEnum(request.getAccountType()) : null;
            Boolean isActive = request.hasIsActive() ? request.getIsActive() : null;
            String search = request.hasSearch() && !request.getSearch().isEmpty() ? 
                    request.getSearch() : null;
            
            // Call service
            Page<UserDTO> deletedUsersPage = userService.getDeletedUsers(
                    pageable, storeId, branchId, roleId, accountType, isActive, search);
            
            // Convert response
            UserListResponse response = grpcMapper.pageToUserListResponse(deletedUsersPage);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            log.info("gRPC GetDeletedUsers completed successfully. Found {} deleted users in {} pages", 
                    deletedUsersPage.getTotalElements(), deletedUsersPage.getTotalPages());
        } catch (Exception e) {
            log.error("Error in gRPC GetDeletedUsers: {}", e.getMessage(), e);
            grpcErrorHandler.handleError(e, responseObserver);
        }
    }
}
