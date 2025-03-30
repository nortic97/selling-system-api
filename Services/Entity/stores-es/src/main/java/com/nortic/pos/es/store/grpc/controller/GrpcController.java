package com.nortic.pos.es.store.grpc.controller;

import com.nortic.pos.es.store.grpc.gen.*;
import com.nortic.pos.es.store.service.StoreSvcImpl;
import com.nortic.pos.es.store.util.GrpcMapper;
import com.nortic.pos.models.dto.StoreDTO;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@GrpcService
public class GrpcController extends StoresServiceGrpc.StoresServiceImplBase {

    private final StoreSvcImpl storeSvc;
    private final GrpcMapper grpcMapper;

    @Autowired
    GrpcController(StoreSvcImpl storeSvc, GrpcMapper grpcMapper) {
        this.storeSvc = storeSvc;
        this.grpcMapper = grpcMapper;
    }

    @Override
    public void createStore(Store request, StreamObserver<Store> responseObserver) {
        super.createStore(request, responseObserver);
    }

    @Override
    public void getStoreById(StoreRequest request, StreamObserver<Store> responseObserver) {
        log.info("getStoreById grpc service");
        try{
            StoreDTO store = storeSvc.getStore(UUID.fromString(request.getId()));
            responseObserver.onNext(grpcMapper.dtoToGrpcModel(store));
            responseObserver.onCompleted();
        } catch (Exception e){
            log.error(e.getMessage());
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error")
                    .asRuntimeException());
        }
    }

    @Override
    public void updateStore(Store request, StreamObserver<Store> responseObserver) {
        super.updateStore(request, responseObserver);
    }

    @Override
    public void deleteStore(StoreRequest request, StreamObserver<DeleteResponse> responseObserver) {
        super.deleteStore(request, responseObserver);
    }

    @Override
    public void softDeleteStore(StoreRequest request, StreamObserver<DeleteResponse> responseObserver) {
        super.softDeleteStore(request, responseObserver);
    }

    @Override
    public void changeStoreStatus(ChangeStatusRequest request, StreamObserver<StoreResponse> responseObserver) {
        super.changeStoreStatus(request, responseObserver);
    }
}
