package com.nortic.pos.es.plan.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.plan.grpc.exception.GrpcException;
import com.nortic.pos.es.plan.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.plan.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.plan.mapper.PlanGrpcMapper;
import com.nortic.pos.es.plan.model.dto.RequestContextDTO;
import com.nortic.pos.es.plan.model.dto.SearchParamsDTO;
import com.nortic.pos.es.plan.service.contract.PlanService;
import com.nortic.pos.es.plan.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class PlanGrpcControllerImpl extends PlanServiceGrpc.PlanServiceImplBase {

    private final PlanService planService;

    private final GrpcException grpcException;

    private final PlanGrpcMapper planGrpcMapper;

    @Override
    public void createPlan(PlanProto.PlanRequest request, StreamObserver<PlanProto.PlanResponse> responseObserver) {
        try {
            log.info("createPlan gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var planDTO = planGrpcMapper.toPlanDTO(request);

            var plan = planService.createPlan(requestContextDTO, planDTO);

            var response = planGrpcMapper.toPlanResponse(plan);

            log.info("createPlan gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createPlan", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void listPlans(PlanProto.PaginationRequest request, StreamObserver<PlanProto.PaginatedPlanResponse> responseObserver) {
        try {
            log.info("listPlans gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedPlans = planService.listPlans(requestContextDTO, pagination);

            var response = planGrpcMapper.toPaginatedPlanResponse(paginatedPlans);

            log.info("listPlans gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("listPlans", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getPlan(PlanProto.PlanIdRequest request, StreamObserver<PlanProto.PlanResponse> responseObserver) {
        try {
            log.info("getPlan gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var plan = planService.getPlan(requestContextDTO, UUID.fromString(request.getId()));

            var response = planGrpcMapper.toPlanResponse(plan);

            log.info("getPlan gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getPlan", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updatePlan(PlanProto.UpdatePlanRequest request, StreamObserver<PlanProto.PlanResponse> responseObserver) {
        try {
            log.info("updatePlan gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var planDTO = planGrpcMapper.toPlanDTO(request);

            var plan = planService.updatePlan(requestContextDTO, UUID.fromString(request.getId()), planDTO);

            var response = planGrpcMapper.toPlanResponse(plan);

            log.info("updatePlan gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updatePlan", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deletePlan(PlanProto.PlanIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deletePlan gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            planService.deletePlan(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deletePlan gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deletePlan", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restorePlan(PlanProto.PlanIdRequest request, StreamObserver<PlanProto.PlanResponse> responseObserver) {
        try {
            log.info("restorePlan gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var plan = planService.restorePlan(requestContextDTO, UUID.fromString(request.getId()));

            var response = planGrpcMapper.toPlanResponse(plan);

            log.info("restorePlan gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restorePlan", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
