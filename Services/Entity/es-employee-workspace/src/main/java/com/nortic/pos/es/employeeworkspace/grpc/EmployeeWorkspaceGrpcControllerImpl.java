package com.nortic.pos.es.employeeworkspace.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.employeeworkspace.grpc.exception.GrpcException;
import com.nortic.pos.es.employeeworkspace.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.employeeworkspace.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.employeeworkspace.mapper.EmployeeWorkspaceGrpcMapper;
import com.nortic.pos.es.employeeworkspace.model.dto.RequestContextDTO;
import com.nortic.pos.es.employeeworkspace.service.contract.EmployeeWorkspaceService;
import com.nortic.pos.es.employeeworkspace.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class EmployeeWorkspaceGrpcControllerImpl extends EmployeeWorkspaceServiceGrpc.EmployeeWorkspaceServiceImplBase {

    private final EmployeeWorkspaceService employeeWorkspaceService;

    private final GrpcException grpcException;

    private final EmployeeWorkspaceGrpcMapper employeeWorkspaceGrpcMapper;

    @Override
    public void getAllEmployeeWorkspaces(EmployeeWorkspaceProto.GetAllEmployeeWorkspacesRequest request, StreamObserver<EmployeeWorkspaceProto.PaginatedEmployeeWorkspaceResponse> responseObserver) {
        log.info("getAllEmployeeWorkspaces gRPC Controller...");

        try {
            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .storeId(ContextKeys.STORE_ID_CONTEXT_KEY.get() != null ? UUID.fromString(ContextKeys.STORE_ID_CONTEXT_KEY.get()) : null)
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());

            var paginatedEmployeeWorkspaces = employeeWorkspaceService.getAllEmployeeWorkspaces(requestContextDTO, pageable);

            var response = employeeWorkspaceGrpcMapper.toPaginatedEmployeeWorkspaceResponse(paginatedEmployeeWorkspaces);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("getAllEmployeeWorkspaces gRPC Controller complete.");
        } catch (Exception e) {
            errorLog("getAllEmployeeWorkspaces", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void createEmployeeWorkspace(EmployeeWorkspaceProto.CreateEmployeeWorkspaceRequest request, StreamObserver<EmployeeWorkspaceProto.EmployeeWorkspaceResponse> responseObserver) {
        try {
            log.info("createEmployeeWorkspace gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var createEmployeeWorkspaceRequestDTO = employeeWorkspaceGrpcMapper.toCreateEmployeeWorkspaceRequestDTO(request);

            var employeeWorkspace = employeeWorkspaceService.createEmployeeWorkspace(requestContextDTO, createEmployeeWorkspaceRequestDTO);

            var response = employeeWorkspaceGrpcMapper.toEmployeeWorkspaceResponse(employeeWorkspace);

            log.info("createEmployeeWorkspace gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createEmployeeWorkspace", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getEmployeeWorkspaceById(EmployeeWorkspaceProto.GetEmployeeWorkspaceByIdRequest request, StreamObserver<EmployeeWorkspaceProto.EmployeeWorkspaceResponse> responseObserver) {
        try {
            log.info("getEmployeeWorkspaceById gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employeeWorkspace = employeeWorkspaceService.getEmployeeWorkspaceById(requestContextDTO, UUID.fromString(request.getId()));

            var response = employeeWorkspaceGrpcMapper.toEmployeeWorkspaceResponse(employeeWorkspace);

            log.info("getEmployeeWorkspaceById gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getEmployeeWorkspaceById", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateEmployeeWorkspace(EmployeeWorkspaceProto.UpdateEmployeeWorkspaceRequest request, StreamObserver<EmployeeWorkspaceProto.EmployeeWorkspaceResponse> responseObserver) {
        try {
            log.info("updateEmployeeWorkspace gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var updateEmployeeWorkspaceRequestDTO = employeeWorkspaceGrpcMapper.toUpdateEmployeeWorkspaceRequestDTO(request);

            var employeeWorkspace = employeeWorkspaceService.updateEmployeeWorkspace(requestContextDTO, UUID.fromString(request.getId()), updateEmployeeWorkspaceRequestDTO);

            var response = employeeWorkspaceGrpcMapper.toEmployeeWorkspaceResponse(employeeWorkspace);

            log.info("updateEmployeeWorkspace gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateEmployeeWorkspace", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteEmployeeWorkspace(EmployeeWorkspaceProto.DeleteEmployeeWorkspaceRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteEmployeeWorkspace gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            employeeWorkspaceService.deleteEmployeeWorkspace(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteEmployeeWorkspace gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteEmployeeWorkspace", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreEmployeeWorkspace(EmployeeWorkspaceProto.RestoreEmployeeWorkspaceRequest request, StreamObserver<EmployeeWorkspaceProto.EmployeeWorkspaceResponse> responseObserver) {
        try {
            log.info("restoreEmployeeWorkspace gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employeeWorkspace = employeeWorkspaceService.restoreEmployeeWorkspace(requestContextDTO, UUID.fromString(request.getId()));

            var response = employeeWorkspaceGrpcMapper.toEmployeeWorkspaceResponse(employeeWorkspace);

            log.info("restoreEmployeeWorkspace gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreEmployeeWorkspace", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateLastAccess(EmployeeWorkspaceProto.UpdateLastAccessRequest request, StreamObserver<EmployeeWorkspaceProto.EmployeeWorkspaceResponse> responseObserver) {
        try {
            log.info("updateLastAccess gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employeeWorkspace = employeeWorkspaceService.updateLastAccess(requestContextDTO, UUID.fromString(request.getId()));

            var response = employeeWorkspaceGrpcMapper.toEmployeeWorkspaceResponse(employeeWorkspace);

            log.info("updateLastAccess gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateLastAccess", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
