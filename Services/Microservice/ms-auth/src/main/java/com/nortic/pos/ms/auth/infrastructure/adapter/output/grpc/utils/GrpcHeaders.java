package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.utils;

import com.nortic.pos.ms.auth.application.port.output.TokenProvider;
import io.grpc.Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GrpcHeaders {

    private final TokenProvider tokenProvider;

    public Metadata generateHeaders(){
        Metadata metadata = new Metadata();

        Metadata.Key<String> authorizationKey =
                Metadata.Key.of("authorization",
                        Metadata.ASCII_STRING_MARSHALLER);

        Metadata.Key<String> storeId =
                Metadata.Key.of("store-id",
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
                storeId,
                UUID.randomUUID().toString()
        );

        metadata.put(
                consumerId,
                "SYSTEM"
        );

        return metadata;
    }

}
