package com.nortic.posdash.us.auth.grpc.controller;

import com.nortic.posdash.us.auth.grpc.gen.AuthRequest;
import com.nortic.posdash.us.auth.grpc.gen.AuthResponse;
import com.nortic.posdash.us.auth.grpc.gen.AuthServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcController extends AuthServiceGrpc.AuthServiceImplBase {
    @Override
    public void authenticate(AuthRequest request, StreamObserver<AuthResponse> responseObserver) {
        // Lógica de autenticación
        String username = request.getUsername();
        String password = request.getPassword();

        // Simulación de autenticación exitosa
        if ("ThnG123".equals(username) && "Password123*".equals(password)) {
            AuthResponse response = AuthResponse.newBuilder()
                    .setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
                    .setRefreshToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
                    .setType("Bearer")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            // Simulación de error de autenticación
            responseObserver.onError(new io.grpc.StatusRuntimeException(io.grpc.Status.UNAUTHENTICATED));
        }
    }
}
