package com.nortic.pos.es.user.grpc.exception;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class GrpcException {

    public void rpcException(String method, Exception e, StreamObserver<?> respObserver) {
        setLog(method, e);
        respObserver.onError(mapStatus(e).
                withDescription(String.format("Method: %s, Error: %s", method, e.getMessage())).
                asException());
    }

    private Status mapStatus(Exception e) {

        if (e instanceof IllegalArgumentException) {
            return Status.INVALID_ARGUMENT;
        }

        if (e instanceof NoSuchElementException) {
            return Status.NOT_FOUND;
        }

        if (e instanceof SecurityException) {
            return Status.PERMISSION_DENIED;
        }

        if (e instanceof TimeoutException) {
            return Status.DEADLINE_EXCEEDED;
        }

        return Status.INTERNAL;
    }

    private void setLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }

}
