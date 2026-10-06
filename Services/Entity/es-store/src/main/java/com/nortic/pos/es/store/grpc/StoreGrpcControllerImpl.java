package com.nortic.pos.es.store.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.store.dto.SearchParamsDTO;
import com.nortic.pos.es.store.dto.request.RequestContextDTO;
import com.nortic.pos.es.store.grpc.exception.GrpcException;
import com.nortic.pos.es.store.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.store.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.store.mapper.StoreGrpcMapper;
import com.nortic.pos.es.store.service.contract.StoreService;
import com.nortic.pos.es.store.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class StoreGrpcControllerImpl extends StoreServiceGrpc.StoreServiceImplBase {

    private final StoreService storeService;

    private final GrpcException grpcException;

    private final StoreGrpcMapper storeGrpcMapper;

    @Override
    public void createStore(StoreProto.StoreRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("createStore gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var storeDTO = storeGrpcMapper.toStoreDTO(request);

            var store = storeService.createStore(requestContextDTO, storeDTO);

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("createStore gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createStore", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void listStores(StoreProto.PaginationRequest request, StreamObserver<StoreProto.PaginatedStoreResponse> responseObserver) {
        try {
            log.info("listStores gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedStores = storeService.listStores(requestContextDTO, pagination);

            var response = storeGrpcMapper.toPaginatedStoreResponse(paginatedStores);

            log.info("listStores gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("listStores", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getStore(StoreProto.StoreIdRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("getStore gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var store = storeService.getStore(requestContextDTO, UUID.fromString(request.getId()));

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("getStore gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getStore", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateStore(StoreProto.UpdateStoreRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("updateStore gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var storeDTO = storeGrpcMapper.toStoreDTO(request);

            var store = storeService.updateStore(requestContextDTO, UUID.fromString(request.getId()), storeDTO);

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("updateStore gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateStore", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteStore(StoreProto.StoreIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteStore gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            storeService.deleteStore(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteStore gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteStore", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeStatus(StoreProto.ChangeStatusRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("changeStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeStatusRequestDTO = storeGrpcMapper.toChangeStatusRequestDTO(request);

            var store = storeService.changeStatus(requestContextDTO, UUID.fromString(request.getId()), changeStatusRequestDTO);

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("changeStatus gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeStatus", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changePhone(StoreProto.ChangePhoneRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("changePhone gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changePhoneRequestDTO = storeGrpcMapper.toChangePhoneRequestDTO(request);

            var store = storeService.changePhone(requestContextDTO, UUID.fromString(request.getId()), changePhoneRequestDTO);

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("changePhone gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changePhone", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeEmail(StoreProto.ChangeEmailRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("changeEmail gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeEmailRequestDTO = storeGrpcMapper.toChangeEmailRequestDTO(request);

            var store = storeService.changeEmail(requestContextDTO, UUID.fromString(request.getId()), changeEmailRequestDTO);

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("changeEmail gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeEmail", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreStore(StoreProto.StoreIdRequest request, StreamObserver<StoreProto.StoreResponse> responseObserver) {
        try {
            log.info("restoreStore gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var store = storeService.restoreStore(requestContextDTO, UUID.fromString(request.getId()));

            var response = storeGrpcMapper.toStoreResponse(store);

            log.info("restoreStore gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreStore", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
