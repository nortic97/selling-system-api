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
        try {
            log.info("changeUserStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeStatusRequestDTO = userGrpcMapper.toChangeUserStatusRequestDTO(request);

            var user = userService.changeUserStatus(requestContextDTO, UUID.fromString(request.getId()), changeStatusRequestDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("changeUserStatus gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeUserStatus", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changePhone(UserProto.ChangePhoneRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("changePhone gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changePhoneRequestDTO = userGrpcMapper.toChangePhoneRequestDTO(request);

            var user = userService.changePhone(requestContextDTO, UUID.fromString(request.getId()), changePhoneRequestDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("changePhone gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changePhone", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeEmail(UserProto.ChangeEmailRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("changeEmail gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeEmailRequestDTO = userGrpcMapper.toChangeEmailRequestDTO(request);

            var user = userService.changeEmail(requestContextDTO, UUID.fromString(request.getId()), changeEmailRequestDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("changeEmail gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeEmail", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeUsername(UserProto.ChangeUsernameRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("changeUsername gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeUsernameRequestDTO = userGrpcMapper.toChangeUsernameRequestDTO(request);

            var user = userService.changeUsername(requestContextDTO, UUID.fromString(request.getId()), changeUsernameRequestDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("changeUsername gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeUsername", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeUserPassword(UserProto.ChangeUserPasswordRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("changeUserPassword gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changePasswordRequestDTO = userGrpcMapper.toChangePasswordRequestDTO(request);

            var user = userService.changeUserPassword(requestContextDTO, UUID.fromString(request.getId()), changePasswordRequestDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("changeUserPassword gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeUserPassword", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreUser(UserProto.RestoreUserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("restoreUser gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var user = userService.restoreUser(requestContextDTO, UUID.fromString(request.getId()));

            var response = userGrpcMapper.toUserResponse(user);

            log.info("restoreUser gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreUser", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteUser(UserProto.DeleteUserRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteUser gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            userService.deleteUser(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteUser gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteUser", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateUser(UserProto.UpdateUserRequest request, StreamObserver<UserProto.UserResponse> responseObserver) {
        try {
            log.info("updateUser gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var userDTO = userGrpcMapper.toUserDTO(request);

            var user = userService.updateUser(requestContextDTO, UUID.fromString(request.getId()), userDTO);

            var response = userGrpcMapper.toUserResponse(user);

            log.info("updateUser gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateUser", e);
            grpcException.grpcException(e, responseObserver);
        }
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
            grpcException.grpcException(e, responseObserver);
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
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void searchUsers(UserProto.SearchUsersRequest request, StreamObserver<UserProto.PaginatedUserResponse> responseObserver) {
        try {
            log.info("searchUsers gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var searchRequestDTO = userGrpcMapper.toUserSearchRequestDTO(request);

            var paginatedUsers = userService.searchUsers(requestContextDTO, searchRequestDTO);

            var response = userGrpcMapper.toPaginatedUserResponse(paginatedUsers);

            log.info("searchUsers gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("searchUsers", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void queryUsers(UserProto.UserQueryRequest request, StreamObserver<UserProto.PaginatedUserResponse> responseObserver) {
        try {
            log.info("queryUsers gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var queryRequestDTO = userGrpcMapper.toUserQueryRequestDTO(request);

            var paginatedUsers = userService.queryUsers(requestContextDTO, queryRequestDTO);

            var response = userGrpcMapper.toPaginatedUserResponse(paginatedUsers);

            log.info("queryUsers gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("queryUsers", e);
            grpcException.grpcException(e, responseObserver);
        }
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
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
