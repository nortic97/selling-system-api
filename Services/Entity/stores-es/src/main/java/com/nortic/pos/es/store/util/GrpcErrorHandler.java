package com.nortic.pos.es.store.util;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcErrorHandler {

    public static void handleError(Exception e, StreamObserver<?> responseObserver) {
        switch (e) {
            case IllegalArgumentException ex -> {
                log.warn("Invalid UUID format: {}", ex.getMessage());
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Invalid store ID format")
                        .asRuntimeException());
            }
            case CustomException ex -> {
                log.warn("Custom error: {}", ex.getMessage());
                responseObserver.onError(Status.NOT_FOUND
                        .withDescription(ex.getMessage())
                        .asRuntimeException());
            }
            default -> {
                log.error("Unexpected error: {}", e.getMessage());
                responseObserver.onError(Status.INTERNAL
                        .withDescription("Internal Server Error")
                        .asRuntimeException());
            }
        }
    }
}
