package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.client;

import com.nortic.pos.es.user.grpc.UserServiceGrpc;
import com.nortic.pos.ms.auth.application.port.output.UserClient;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.domain.model.User;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper.UserGrpcMapper;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGrpcClient implements UserClient {

    private final UserServiceGrpc.UserServiceBlockingStub userStub;
    private final UserGrpcMapper userGrpcMapper;

    @Override
    public User authenticate(LoginCredentials loginCredentials) {
        log.info("Authenticating user grpc: {}", loginCredentials.getUsername());

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
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjNlNDU2Ny1lODliLTEyZDMtYTQ1Ni00MjY2MTQxNzQwMDAiLCJ1c2VybmFtZSI6Impkbm92YSIsImVtYWlsIjoiamRub3ZhQGV4YW1wbGUuY29tIiwicGVybWlzc2lvbnMiOlsic3lzdGVtLmFsbCJdLCJmaXJzdF9uYW1lIjoiSnVhbiIsImxhc3RfbmFtZSI6Ik5vdmEiLCJpYXQiOjE3OTQ5NDExMTAsImV4cCI6MTc5NTAyNzUxMH0.0g8qm7LK7VosX7XUl9RJ9oV2gTY8J7hDIldUM1n2il0"
        );

        metadata.put(
                correlationId,
                "7ded96f6-f76a-4c1e-8140-aef781d848c6"
        );

        metadata.put(
                consumerId,
                "SVP"
        );

        var credentials = userGrpcMapper.toAuthenticateRequest(loginCredentials);
        var response = userStub
                .withInterceptors(
                        MetadataUtils.newAttachHeadersInterceptor(metadata)
                )
                .authenticate(credentials);

        return User.builder().build();
    }
}
