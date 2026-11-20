package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.client;

import com.nortic.pos.es.user.grpc.UserServiceGrpc;
import com.nortic.pos.ms.auth.application.port.output.UserClient;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.User;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper.UserGrpcMapper;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.utils.GrpcHeaders;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGrpcClient implements UserClient {

    private final UserServiceGrpc.UserServiceBlockingStub userStub;
    private final GrpcHeaders grpcHeaders;
    private final UserGrpcMapper userGrpcMapper;

    @Override
    public User authenticate(LoginCredentials loginCredentials) {
        log.info("Authenticating user grpc: {}", loginCredentials.getUsername());

        var credentials = userGrpcMapper.toAuthenticateRequest(loginCredentials);
        var response = userStub
                .withInterceptors(
                        MetadataUtils.newAttachHeadersInterceptor(grpcHeaders.generateHeaders())
                )
                .authenticate(credentials);
    log.info("Authenticate user grpc finalized...");
        return userGrpcMapper.toUser(response);
    }


}
