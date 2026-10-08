package com.nortic.pos.es.plan.grpc.exception;

import com.nortic.pos.es.plan.exception.BusinessException;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class GrpcException {

    private record GrpcError(Status status, String message) {
    }

    public void grpcException(Exception exception, StreamObserver<?> responseObserver) {

        log.error("gRPC error occurred: {}", exception.getMessage(), exception);

        var grpcError = mapGrpcError(exception);

        responseObserver.onError(grpcError.status().withDescription(grpcError.message()).asException());
    }

    private GrpcError mapGrpcError(Exception exception) {

        if (exception instanceof BusinessException businessException) {

            var code = businessException.getExceptionCode();

            return switch (code) {

                case PLAN_NOT_FOUND -> new GrpcError(Status.NOT_FOUND, code.getMessage());

                case DATA_INTEGRITY_VIOLATION -> new GrpcError(Status.ALREADY_EXISTS, code.getMessage());

                case VALIDATION_FAILED, MISSING_HEADER ->
                        new GrpcError(Status.INVALID_ARGUMENT, code.getMessage());

                case MISSING_PERMISSION -> new GrpcError(Status.PERMISSION_DENIED, code.getMessage());

                default -> new GrpcError(Status.INTERNAL, "Internal server error.");
            };
        }

        if (exception instanceof IllegalArgumentException) {
            return new GrpcError(Status.INVALID_ARGUMENT, "Invalid request.");
        }

        if (exception instanceof NoSuchElementException) {
            return new GrpcError(Status.NOT_FOUND, "Resource not found.");
        }

        if (exception instanceof SecurityException) {
            return new GrpcError(Status.PERMISSION_DENIED, "Access denied.");
        }

        if (exception instanceof TimeoutException) {
            return new GrpcError(Status.DEADLINE_EXCEEDED, "Request timeout.");
        }

        return new GrpcError(Status.INTERNAL, "Internal server error.");
    }

}
