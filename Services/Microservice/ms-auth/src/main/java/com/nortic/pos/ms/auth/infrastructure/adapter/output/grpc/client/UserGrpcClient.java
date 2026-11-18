package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.client;

import com.nortic.pos.es.user.grpc.UserServiceGrpc;
import com.nortic.pos.ms.auth.application.port.output.TokenProvider;
import com.nortic.pos.ms.auth.application.port.output.UserClient;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.User;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper.UserGrpcMapper;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGrpcClient implements UserClient {

    private final UserServiceGrpc.UserServiceBlockingStub userStub;
    private final TokenProvider tokenProvider;
    private final UserGrpcMapper userGrpcMapper;

    @Override
    public User authenticate(LoginCredentials loginCredentials) {
        log.info("Authenticating user grpc: {}", loginCredentials.getUsername());

        var credentials = userGrpcMapper.toAuthenticateRequest(loginCredentials);
        var response = userStub
                .withInterceptors(
                        MetadataUtils.newAttachHeadersInterceptor(generateHeaders())
                )
                .authenticate(credentials);
    log.info("Authenticate user grpc finalized...");
        return userGrpcMapper.toUser(response);
    }

    private Metadata generateHeaders(){
        Metadata metadata = new Metadata();

        Metadata.Key<String> authorizationKey =
                Metadata.Key.of("authorization",
                        Metadata.ASCII_STRING_MARSHALLER);

        Metadata.Key<String> correlationId =
                Metadata.Key.of("correlation-id",
                        Metadata.ASCII_STRING_MARSHALLER);

        Metadata.Key<String> consumerId =
                Metadata.Key.of("consumer-id",
                        Metadata.ASCII_STRING_MARSHALLER);

        metadata.put(
                authorizationKey,
                "Bearer " + tokenProvider.generateInternalJwt()
        );

        metadata.put(
                correlationId,
                UUID.randomUUID().toString()
        );

        metadata.put(
                consumerId,
                "SYSTEM"
        );

        return metadata;
    }
}
