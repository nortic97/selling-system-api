package com.nortic.pos.es.store.grpc;

import com.nortic.pos.es.store.grpc.gen.*;
import com.nortic.pos.es.store.service.StoreSvcImpl;
import com.nortic.pos.es.store.util.GrpcMapper;
import com.nortic.pos.models.dto.StoreDTO;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.nortic.pos.es.store.util.GrpcErrorHandler.handleError;

@Slf4j
@GrpcService
public class GrpcServiceImpl extends StoreServiceGrpc.StoreServiceImplBase {

    private final StoreSvcImpl storeSvc;
    private final GrpcMapper grpcMapper;

    @Autowired
    GrpcServiceImpl(StoreSvcImpl storeSvc, GrpcMapper grpcMapper) {
        this.storeSvc = storeSvc;
        this.grpcMapper = grpcMapper;
    }

    @Override
    public void createStore(Store request, StreamObserver<Store> responseObserver) {
        log.info("Creating store gRPC Service");
        try {
            StoreDTO store = storeSvc.createStore(grpcMapper.grpcModelToDto(request));
            responseObserver.onNext(grpcMapper.dtoToGrpcModel(store));
            responseObserver.onCompleted();
        } catch (Exception e) {
            handleError(e, responseObserver);
        }
    }

    @Override
    public void getStoreById(StoreRequest request, StreamObserver<Store> responseObserver) {
        log.info("Get Store by Id gRPC service");
        try {
            StoreDTO store = storeSvc.getStore(UUID.fromString(request.getId()));
            responseObserver.onNext(grpcMapper.dtoToGrpcModel(store));
            responseObserver.onCompleted();
        } catch (Exception e) {
            handleError(e, responseObserver);
        }
    }

    @Override
    public void updateStore(Store request, StreamObserver<Store> responseObserver) {
        log.info("Update Store gRPC Service");
        try {
            StoreDTO store = storeSvc.updateStore(UUID.fromString(request.getId()), grpcMapper.grpcModelToDto(request));
            responseObserver.onNext(grpcMapper.dtoToGrpcModel(store));
            responseObserver.onCompleted();
        } catch (Exception e) {
            handleError(e, responseObserver);
        }
    }

    @Override
    public void deleteStore(StoreRequest request, StreamObserver<DeleteResponse> responseObserver) {
        log.info("Delete Store gRPC Service");
        try {
            storeSvc.deleteStore(UUID.fromString(request.getId()));
            responseObserver.onNext(DeleteResponse.newBuilder().setSuccess(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            handleError(e, responseObserver);
        }
    }

    @Override
    public void softDeleteStore(StoreRequest request, StreamObserver<DeleteResponse> responseObserver) {
        log.info("Soft Delete Store gRPC Service");
        try {
            storeSvc.softDeleteStore(UUID.fromString(request.getId()));
            responseObserver.onNext(DeleteResponse.newBuilder().setSuccess(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            handleError(e, responseObserver);
        }
    }

    @Override
    public void changeStoreStatus(ChangeStatusRequest request, StreamObserver<StoreResponse> responseObserver) {
        log.info("Change Store Status gRPC Service");
        try {
            storeSvc.changeStoreStatus(UUID.fromString(request.getId()), request.getStatus());
            responseObserver.onNext(StoreResponse.newBuilder().setSuccess(true).build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            handleError(e, responseObserver);
        }
    }
}
