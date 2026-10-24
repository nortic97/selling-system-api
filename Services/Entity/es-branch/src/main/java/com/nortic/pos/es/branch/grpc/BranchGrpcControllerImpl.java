package com.nortic.pos.es.branch.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.branch.grpc.exception.GrpcException;
import com.nortic.pos.es.branch.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.branch.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.branch.mapper.BranchGrpcMapper;
import com.nortic.pos.es.branch.model.dto.RequestContextDTO;
import com.nortic.pos.es.branch.model.dto.SearchParamsDTO;
import com.nortic.pos.es.branch.model.dto.StatusRequest;
import com.nortic.pos.es.branch.service.contract.BranchService;
import com.nortic.pos.es.branch.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class BranchGrpcControllerImpl extends BranchServiceGrpc.BranchServiceImplBase {

    private final BranchService branchService;

    private final GrpcException grpcException;

    private final BranchGrpcMapper branchGrpcMapper;

    @Override
    public void createBranch(BranchProto.BranchRequest request, StreamObserver<BranchProto.BranchResponse> responseObserver) {
        try {
            log.info("createBranch gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var branchDTO = branchGrpcMapper.toBranchDTO(request);

            var branch = branchService.createBranch(requestContextDTO, branchDTO);

            var response = branchGrpcMapper.toBranchResponse(branch);

            log.info("createBranch gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createBranch", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void listBranches(BranchProto.PaginationRequest request, StreamObserver<BranchProto.PaginatedBranchResponse> responseObserver) {
        try {
            log.info("listBranches gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedBranches = branchService.getAllBranches(requestContextDTO, pagination);

            var response = branchGrpcMapper.toPaginatedBranchResponse(paginatedBranches);

            log.info("listBranches gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("listBranches", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getBranch(BranchProto.BranchIdRequest request, StreamObserver<BranchProto.BranchResponse> responseObserver) {
        try {
            log.info("getBranch gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var branch = branchService.getBranch(requestContextDTO, UUID.fromString(request.getId()));

            var response = branchGrpcMapper.toBranchResponse(branch);

            log.info("getBranch gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getBranch", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateBranch(BranchProto.UpdateBranchRequest request, StreamObserver<BranchProto.BranchResponse> responseObserver) {
        try {
            log.info("updateBranch gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var branchDTO = branchGrpcMapper.toBranchDTO(request);

            var branch = branchService.updateBranch(requestContextDTO, UUID.fromString(request.getId()), branchDTO);

            var response = branchGrpcMapper.toBranchResponse(branch);

            log.info("updateBranch gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateBranch", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteBranch(BranchProto.BranchIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteBranch gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            branchService.deleteBranch(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteBranch gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteBranch", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreBranch(BranchProto.BranchIdRequest request, StreamObserver<BranchProto.BranchResponse> responseObserver) {
        try {
            log.info("restoreBranch gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var branch = branchService.restoreBranch(requestContextDTO, UUID.fromString(request.getId()));

            var response = branchGrpcMapper.toBranchResponse(branch);

            log.info("restoreBranch gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreBranch", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeStatus(BranchProto.ChangeStatusRequest request, StreamObserver<BranchProto.BranchResponse> responseObserver) {
        try {
            log.info("changeStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var statusRequest = StatusRequest.builder()
                    .active(request.getActive())
                    .build();

            var branch = branchService.changeStatus(requestContextDTO, UUID.fromString(request.getId()), statusRequest);

            var response = branchGrpcMapper.toBranchResponse(branch);

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
