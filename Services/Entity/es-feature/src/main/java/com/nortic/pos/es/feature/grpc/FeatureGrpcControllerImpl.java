package com.nortic.pos.es.feature.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.feature.grpc.exception.GrpcException;
import com.nortic.pos.es.feature.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.feature.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.feature.mapper.FeatureGrpcMapper;
import com.nortic.pos.es.feature.model.dto.RequestContextDTO;
import com.nortic.pos.es.feature.model.dto.SearchParamsDTO;
import com.nortic.pos.es.feature.service.contract.FeatureService;
import com.nortic.pos.es.feature.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class FeatureGrpcControllerImpl extends FeatureServiceGrpc.FeatureServiceImplBase {

    private final FeatureService featureService;

    private final GrpcException grpcException;

    private final FeatureGrpcMapper featureGrpcMapper;

    @Override
    public void createFeature(FeatureProto.FeatureRequest request, StreamObserver<FeatureProto.FeatureResponse> responseObserver) {
        try {
            log.info("createFeature gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var featureDTO = featureGrpcMapper.toFeatureDTO(request);

            var feature = featureService.createFeature(requestContextDTO, featureDTO);

            var response = featureGrpcMapper.toFeatureResponse(feature);

            log.info("createFeature gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createFeature", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void listFeatures(FeatureProto.PaginationRequest request, StreamObserver<FeatureProto.PaginatedFeatureResponse> responseObserver) {
        try {
            log.info("listFeatures gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedFeatures = featureService.listFeatures(requestContextDTO, pagination);

            var response = featureGrpcMapper.toPaginatedFeatureResponse(paginatedFeatures);

            log.info("listFeatures gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("listFeatures", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getFeature(FeatureProto.FeatureIdRequest request, StreamObserver<FeatureProto.FeatureResponse> responseObserver) {
        try {
            log.info("getFeature gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var feature = featureService.getFeature(requestContextDTO, UUID.fromString(request.getId()));

            var response = featureGrpcMapper.toFeatureResponse(feature);

            log.info("getFeature gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getFeature", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateFeature(FeatureProto.UpdateFeatureRequest request, StreamObserver<FeatureProto.FeatureResponse> responseObserver) {
        try {
            log.info("updateFeature gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var featureDTO = featureGrpcMapper.toFeatureDTO(request);

            var feature = featureService.updateFeature(requestContextDTO, UUID.fromString(request.getId()), featureDTO);

            var response = featureGrpcMapper.toFeatureResponse(feature);

            log.info("updateFeature gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateFeature", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteFeature(FeatureProto.FeatureIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteFeature gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            featureService.deleteFeature(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteFeature gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteFeature", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreFeature(FeatureProto.FeatureIdRequest request, StreamObserver<FeatureProto.FeatureResponse> responseObserver) {
        try {
            log.info("restoreFeature gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var feature = featureService.restoreFeature(requestContextDTO, UUID.fromString(request.getId()));

            var response = featureGrpcMapper.toFeatureResponse(feature);

            log.info("restoreFeature gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreFeature", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeStatus(FeatureProto.ChangeStatusRequest request, StreamObserver<FeatureProto.FeatureResponse> responseObserver) {
        try {
            log.info("changeStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var featureDTO = featureService.getFeature(requestContextDTO, UUID.fromString(request.getId()));
            featureDTO.setIsActive(request.getIsActive());

            var updatedFeatureDTO = featureService.updateFeature(requestContextDTO, UUID.fromString(request.getId()), featureDTO);

            var response = featureGrpcMapper.toFeatureResponse(updatedFeatureDTO);

            log.info("changeStatus gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeStatus", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
