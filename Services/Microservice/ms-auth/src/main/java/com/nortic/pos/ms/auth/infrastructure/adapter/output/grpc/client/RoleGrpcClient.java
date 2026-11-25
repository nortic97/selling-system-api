package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.client;

import com.nortic.pos.es.role.grpc.RoleServiceGrpc;
import com.nortic.pos.ms.auth.application.port.output.RoleClient;
import com.nortic.pos.ms.auth.domain.model.PermissionResponseDTO;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper.RoleGrpcMapper;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.utils.GrpcHeaders;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleGrpcClient implements RoleClient {

    private final RoleServiceGrpc.RoleServiceBlockingStub roleStub;
    private final GrpcHeaders grpcHeaders;
    private final RoleGrpcMapper roleGrpcMapper;

    @Override
    public List<PermissionResponseDTO> getRolePermission(UUID roleId) {
        log.info("Get role permissions by ID {}", roleId);
        var permissions = roleStub.withInterceptors(
                MetadataUtils.newAttachHeadersInterceptor(grpcHeaders.generateHeaders())
        ).getRolePermission(roleGrpcMapper.toPermissionIdRequest(roleId));
        log.info("Get role permissions by ID: {} Finalized...",roleId);
        return roleGrpcMapper.toPermissionResponseDTO(permissions);
    }
}
