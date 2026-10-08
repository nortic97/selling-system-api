package com.nortic.pos.es.permission.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.permission.grpc.exception.GrpcException;
import com.nortic.pos.es.permission.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.permission.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.permission.mapper.PermissionGrpcMapper;
import com.nortic.pos.es.permission.model.dto.RequestContextDTO;
import com.nortic.pos.es.permission.model.dto.SearchParamsDTO;
import com.nortic.pos.es.permission.service.contract.PermissionService;
import com.nortic.pos.es.permission.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class PermissionGrpcControllerImpl extends PermissionServiceGrpc.PermissionServiceImplBase {

    private final PermissionService permissionService;

    private final GrpcException grpcException;

    private final PermissionGrpcMapper permissionGrpcMapper;

    @Override
    public void createPermission(PermissionProto.PermissionRequest request, StreamObserver<PermissionProto.PermissionResponse> responseObserver) {
        try {
            log.info("createPermission gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permissionDTO = permissionGrpcMapper.toPermissionDTO(request);

            var permission = permissionService.createPermission(requestContextDTO, permissionDTO);

            var response = permissionGrpcMapper.toPermissionResponse(permission);

            log.info("createPermission gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createPermission", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void listPermissions(PermissionProto.PaginationRequest request, StreamObserver<PermissionProto.PaginatedPermissionResponse> responseObserver) {
        try {
            log.info("listPermissions gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedPermissions = permissionService.listPermissions(requestContextDTO, pagination);

            var response = permissionGrpcMapper.toPaginatedPermissionResponse(paginatedPermissions);

            log.info("listPermissions gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("listPermissions", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getPermission(PermissionProto.PermissionIdRequest request, StreamObserver<PermissionProto.PermissionResponse> responseObserver) {
        try {
            log.info("getPermission gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permission = permissionService.getPermission(requestContextDTO, UUID.fromString(request.getId()));

            var response = permissionGrpcMapper.toPermissionResponse(permission);

            log.info("getPermission gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getPermission", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updatePermission(PermissionProto.UpdatePermissionRequest request, StreamObserver<PermissionProto.PermissionResponse> responseObserver) {
        try {
            log.info("updatePermission gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permissionDTO = permissionGrpcMapper.toPermissionDTO(request);

            var permission = permissionService.updatePermission(requestContextDTO, UUID.fromString(request.getId()), permissionDTO);

            var response = permissionGrpcMapper.toPermissionResponse(permission);

            log.info("updatePermission gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updatePermission", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deletePermission(PermissionProto.PermissionIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deletePermission gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            permissionService.deletePermission(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deletePermission gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deletePermission", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeStatus(PermissionProto.ChangeStatusRequest request, StreamObserver<PermissionProto.PermissionResponse> responseObserver) {
        try {
            log.info("changeStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeStatusRequestDTO = permissionGrpcMapper.toChangeStatusRequestDTO(request);

            var permission = permissionService.changeStatus(requestContextDTO, UUID.fromString(request.getId()), changeStatusRequestDTO);

            var response = permissionGrpcMapper.toPermissionResponse(permission);

            log.info("changeStatus gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeStatus", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restorePermission(PermissionProto.PermissionIdRequest request, StreamObserver<PermissionProto.PermissionResponse> responseObserver) {
        try {
            log.info("restorePermission gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permission = permissionService.restorePermission(requestContextDTO, UUID.fromString(request.getId()));

            var response = permissionGrpcMapper.toPermissionResponse(permission);

            log.info("restorePermission gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restorePermission", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
