package com.nortic.pos.es.user.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.user.dto.SearchParamsDTO;
import com.nortic.pos.es.user.dto.request.RequestContextDTO;
import com.nortic.pos.es.user.grpc.exception.GrpcException;
import com.nortic.pos.es.user.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.user.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.user.mapper.UserGrpcMapper;
import com.nortic.pos.es.user.service.contract.UserService;
import com.nortic.pos.es.user.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class UserGrpcControllerImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    private final GrpcException grpcException;

    private final UserGrpcMapper userGrpcMapper;

    @Override
    public void changeUserStatus(UserProto.ChangeUserStatusRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.changeUserStatus(request, responseObserver);
    }

    @Override
    public void changePhone(UserProto.ChangePhoneRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.changePhone(request, responseObserver);
    }

    @Override
    public void changeEmail(UserProto.ChangeEmailRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.changeEmail(request, responseObserver);
    }

    @Override
    public void changeUsername(UserProto.ChangeUsernameRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.changeUsername(request, responseObserver);
    }

    @Override
    public void changeUserPassword(UserProto.ChangeUserPasswordRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.changeUserPassword(request, responseObserver);
    }

    @Override
    public void restoreUser(UserProto.RestoreUserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.restoreUser(request, responseObserver);
    }

    @Override
    public void deleteUser(UserProto.DeleteUserRequest request, StreamObserver<Empty> responseObserver) {
        super.deleteUser(request, responseObserver);
    }

    @Override
    public void updateUser(UserProto.UpdateUserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        super.updateUser(request, responseObserver);
    }

    @Override
    public void createUser(UserProto.CreateUserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("createUser gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var userDTO = userGrpcMapper.toUserDTO(request);

            var user = userService.createUser(requestContextDTO, userDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("createUser gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createUser", e);
            grpcException.grpcException("createUser", e, responseObserver);
        }
    }

    @Override
    public void getUserById(UserProto.GetUserByIdRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {

            log.info("getUserById gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var user = userService.getUserById(requestContextDTO, UUID.fromString(request.getId()));

            var response = userGrpcMapper.toUserResponse(user);

            log.info("getUserById gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getUserById", e);
            grpcException.grpcException("getUserById", e, responseObserver);
        }
    }

    @Override
    public void searchUsers(UserProto.SearchUsersRequest request, StreamObserver<UserProto.PaginatedUserResponse> responseObserver) {
        super.searchUsers(request, responseObserver);
    }

    @Override
    public void queryUsers(UserProto.UserQueryRequest request, StreamObserver<UserProto.PaginatedUserResponse> responseObserver) {
        super.queryUsers(request, responseObserver);
    }

    @Override
    public void getAllUsers(UserProto.GetAllUsersRequest request, StreamObserver<UserProto.PaginatedUserResponse> responseObserver) {
        log.info("getAllUsers gRPC Controller...");

        try {
            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedUsers = userService.getAllUsers(requestContextDTO, pagination);

            var response = userGrpcMapper.toPaginatedUserResponse(paginatedUsers);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("getAllUsers gRPC Controller complete.");
        } catch (Exception e) {
            errorLog("getAllUsers", e);
            grpcException.grpcException("getAllUsers", e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
