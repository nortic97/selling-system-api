package com.nortic.pos.es.role.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.role.grpc.exception.GrpcException;
import com.nortic.pos.es.role.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.role.mapper.RoleGrpcMapper;
import com.nortic.pos.es.role.model.dto.RequestContextDTO;
import com.nortic.pos.es.role.model.dto.SearchParamsDTO;
import com.nortic.pos.es.role.service.contract.RoleService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@GrpcService
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

            Pageable pageable = createPageable(request);

            var searchParamsDTO = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var roles = roleService.listRoles(requestContextDTO, searchParamsDTO);

            var response = roleGrpcMapper.toPaginatedRoleResponse(roles);

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

    @Override
    public void getRolePermission(RoleProto.RoleIdRequest request, StreamObserver<RoleProto.PermissionResponse> responseObserver) {
        try {
            log.info("getRolePermission gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permissions = roleService.getRolePermission(requestContextDTO, UUID.fromString(request.getId()));

            for (var permission : permissions) {
                var response = roleGrpcMapper.toPermissionResponse(permission);
                responseObserver.onNext(response);
            }

            log.info("getRolePermission gRPC Controller Completed");

            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getRolePermission", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void addPermissionToRole(RoleProto.PermissionRequest request, StreamObserver<RoleProto.PermissionResponse> responseObserver) {
        try {
            log.info("addPermissionToRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permissionRequestDTO = roleGrpcMapper.toPermissionRequestDTO(request);

            var permissions = roleService.addPermissionToRole(requestContextDTO, UUID.fromString(request.getRoleId()), permissionRequestDTO);

            for (var permission : permissions) {
                var response = roleGrpcMapper.toPermissionResponse(permission);
                responseObserver.onNext(response);
            }

            log.info("addPermissionToRole gRPC Controller Completed");

            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("addPermissionToRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void removePermissionsFromRole(RoleProto.PermissionRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("removePermissionsFromRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var permissionRequestDTO = roleGrpcMapper.toPermissionRequestDTO(request);

            roleService.removePermissionsFromRole(requestContextDTO, UUID.fromString(request.getRoleId()), permissionRequestDTO);

            log.info("removePermissionsFromRole gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("removePermissionsFromRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void removePermissionFromRole(RoleProto.PermissionIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("removePermissionFromRole gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            roleService.removePermissionFromRole(requestContextDTO, UUID.fromString(request.getRoleId()), UUID.fromString(request.getPermissionId()));

            log.info("removePermissionFromRole gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("removePermissionFromRole", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }

    private Pageable createPageable(RoleProto.PaginationRequest request) {

        int page = Math.max(0, request.getPage() - 1);
        int size = request.getSize() > 0 ? request.getSize() : 10;

        List<Sort.Order> orders = new ArrayList<>();

        for (String sortParam : request.getSortList()) {
            if (sortParam != null && !sortParam.isBlank()) {
                String[] parts = sortParam.split(",");
                String property = parts[0].trim();
                Sort.Direction direction = (parts.length > 1 && "desc".equalsIgnoreCase(parts[1].trim()))
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;
                orders.add(new Sort.Order(direction, property));
            }
        }

        Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);

        return PageRequest.of(page, size, sort);
    }
}
