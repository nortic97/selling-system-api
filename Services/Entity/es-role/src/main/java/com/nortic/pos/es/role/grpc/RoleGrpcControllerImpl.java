package com.nortic.pos.es.role.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.role.grpc.exception.GrpcException;
import com.nortic.pos.es.role.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.role.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.role.mapper.RoleGrpcMapper;
import com.nortic.pos.es.role.model.dto.RequestContextDTO;
import com.nortic.pos.es.role.model.dto.SearchParamsDTO;
import com.nortic.pos.es.role.service.contract.RoleService;
import com.nortic.pos.es.role.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class RoleGrpcControllerImpl extends RoleServiceGrpc.RoleServiceImplBase {

    private final RoleService roleService;

    private final GrpcException grpcException;

    private final RoleGrpcMapper roleGrpcMapper;

    @Override
    public void createRole(RoleProto.RoleRequest request, StreamObserver<RoleProto.RoleResponse> responseObserver) {
        try {
            log.info("createRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var roleDTO = roleGrpcMapper.toRoleDTO(request);

            var role = roleService.createRole(requestContextDTO, roleDTO);

            var response = roleGrpcMapper.toRoleResponse(role);

            log.info("createRole gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void listRoles(RoleProto.PaginationRequest request, StreamObserver<RoleProto.PaginatedRoleResponse> responseObserver) {
        try {
            log.info("listRoles gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedRoles = roleService.listRoles(requestContextDTO, pagination);

            var response = roleGrpcMapper.toPaginatedRoleResponse(paginatedRoles);

            log.info("listRoles gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("listRoles", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getRole(RoleProto.RoleIdRequest request, StreamObserver<RoleProto.RoleResponse> responseObserver) {
        try {
            log.info("getRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var role = roleService.getRole(requestContextDTO, UUID.fromString(request.getId()));

            var response = roleGrpcMapper.toRoleResponse(role);

            log.info("getRole gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateRole(RoleProto.UpdateRoleRequest request, StreamObserver<RoleProto.RoleResponse> responseObserver) {
        try {
            log.info("updateRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var roleDTO = roleGrpcMapper.toRoleDTO(request);

            var role = roleService.updateRole(requestContextDTO, UUID.fromString(request.getId()), roleDTO);

            var response = roleGrpcMapper.toRoleResponse(role);

            log.info("updateRole gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteRole(RoleProto.RoleIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            roleService.deleteRole(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteRole gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeStatus(RoleProto.ChangeStatusRequest request, StreamObserver<RoleProto.RoleResponse> responseObserver) {
        try {
            log.info("changeStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeStatusRequestDTO = roleGrpcMapper.toChangeStatusRequestDTO(request);

            var role = roleService.changeStatus(requestContextDTO, UUID.fromString(request.getId()), changeStatusRequestDTO);

            var response = roleGrpcMapper.toRoleResponse(role);

            log.info("changeStatus gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeStatus", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreRole(RoleProto.RoleIdRequest request, StreamObserver<RoleProto.RoleResponse> responseObserver) {
        try {
            log.info("restoreRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var role = roleService.restoreRole(requestContextDTO, UUID.fromString(request.getId()));

            var response = roleGrpcMapper.toRoleResponse(role);

            log.info("restoreRole gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
