package com.nortic.pos.es.employee.grpc;

import com.google.protobuf.Empty;
import com.nortic.pos.es.employee.grpc.exception.GrpcException;
import com.nortic.pos.es.employee.grpc.interceptor.ContextKeys;
import com.nortic.pos.es.employee.grpc.interceptor.GrpcInterceptor;
import com.nortic.pos.es.employee.mapper.EmployeeGrpcMapper;
import com.nortic.pos.es.employee.model.dto.RequestContextDTO;
import com.nortic.pos.es.employee.model.dto.SearchParamsDTO;
import com.nortic.pos.es.employee.service.contract.EmployeeService;
import com.nortic.pos.es.employee.util.PageableUtil;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService(interceptors = GrpcInterceptor.class)
@RequiredArgsConstructor
public class EmployeeGrpcControllerImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    private final EmployeeService employeeService;

    private final GrpcException grpcException;

    private final EmployeeGrpcMapper employeeGrpcMapper;

    @Override
    public void changeEmployeeStatus(EmployeeProto.ChangeEmployeeStatusRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("changeEmployeeStatus gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeStatusRequestDTO = employeeGrpcMapper.toChangeEmployeeStatusRequestDTO(request);

            var employee = employeeService.changeEmployeeStatus(requestContextDTO, UUID.fromString(request.getId()), changeStatusRequestDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("changeEmployeeStatus gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeEmployeeStatus", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changePhone(EmployeeProto.ChangePhoneRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("changePhone gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changePhoneRequestDTO = employeeGrpcMapper.toChangePhoneRequestDTO(request);

            var employee = employeeService.changePhone(requestContextDTO, UUID.fromString(request.getId()), changePhoneRequestDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("changePhone gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changePhone", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeEmail(EmployeeProto.ChangeEmailRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("changeEmail gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeEmailRequestDTO = employeeGrpcMapper.toChangeEmailRequestDTO(request);

            var employee = employeeService.changeEmail(requestContextDTO, UUID.fromString(request.getId()), changeEmailRequestDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("changeEmail gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeEmail", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeUsername(EmployeeProto.ChangeUsernameRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("changeusername gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changeusernameRequestDTO = employeeGrpcMapper.toChangeusernameRequestDTO(request);

            var employee = employeeService.changeUsername(requestContextDTO, UUID.fromString(request.getId()), changeusernameRequestDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("changeusername gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeusername", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void authenticate(EmployeeProto.AuthenticateRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("authenticate gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var authenticateRequestDTO = employeeGrpcMapper.toAuthenticateRequestDTO(request);

            var employee = employeeService.authenticate(requestContextDTO, authenticateRequestDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("authenticate gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("authenticate", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void changeEmployeePassword(EmployeeProto.ChangeEmployeePasswordRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("changeEmployeePassword gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var changePasswordRequestDTO = employeeGrpcMapper.toChangePasswordRequestDTO(request);

            var employee = employeeService.changeEmployeePassword(requestContextDTO, UUID.fromString(request.getId()), changePasswordRequestDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("changeEmployeePassword gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("changeEmployeePassword", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void restoreEmployee(EmployeeProto.RestoreEmployeeRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("restoreEmployee gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employee = employeeService.restoreEmployee(requestContextDTO, UUID.fromString(request.getId()));

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("restoreEmployee gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("restoreEmployee", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void deleteEmployee(EmployeeProto.DeleteEmployeeRequest request, StreamObserver<Empty> responseObserver) {
        try {
            log.info("deleteEmployee gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            employeeService.deleteEmployee(requestContextDTO, UUID.fromString(request.getId()));

            log.info("deleteEmployee gRPC Controller Completed");

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("deleteEmployee", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void updateEmployee(EmployeeProto.UpdateEmployeeRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("updateEmployee gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employeeDTO = employeeGrpcMapper.toEmployeeDTO(request);

            var employee = employeeService.updateEmployee(requestContextDTO, UUID.fromString(request.getId()), employeeDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("updateEmployee gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("updateEmployee", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void createEmployee(EmployeeProto.CreateEmployeeRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {
            log.info("createEmployee gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employeeDTO = employeeGrpcMapper.toEmployeeDTO(request);

            var employee = employeeService.createEmployee(requestContextDTO, employeeDTO);

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("createEmployee gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("createEmployee", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getEmployeeById(EmployeeProto.GetEmployeeByIdRequest request, StreamObserver<EmployeeProto.EmployeeResponse> responseObserver) {
        try {

            log.info("getEmployeeById gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .build();

            var employee = employeeService.getEmployeeById(requestContextDTO, UUID.fromString(request.getId()));

            var response = employeeGrpcMapper.toEmployeeResponse(employee);

            log.info("getEmployeeById gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("getEmployeeById", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void searchEmployees(EmployeeProto.SearchEmployeesRequest request, StreamObserver<EmployeeProto.PaginatedEmployeeResponse> responseObserver) {
        try {
            log.info("searchEmployees gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .storeId(ContextKeys.STORE_ID_CONTEXT_KEY.get() != null ? UUID.fromString(ContextKeys.STORE_ID_CONTEXT_KEY.get()) : null)
                    .build();

            var searchRequestDTO = employeeGrpcMapper.toEmployeeSearchRequestDTO(request);

            var paginatedEmployees = employeeService.searchEmployees(requestContextDTO, searchRequestDTO);

            var response = employeeGrpcMapper.toPaginatedEmployeeResponse(paginatedEmployees);

            log.info("searchEmployees gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("searchEmployees", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void queryEmployees(EmployeeProto.EmployeeQueryRequest request, StreamObserver<EmployeeProto.PaginatedEmployeeResponse> responseObserver) {
        try {
            log.info("queryEmployees gRPC Controller...");

            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .storeId(ContextKeys.STORE_ID_CONTEXT_KEY.get() != null ? UUID.fromString(ContextKeys.STORE_ID_CONTEXT_KEY.get()) : null)
                    .build();

            var queryRequestDTO = employeeGrpcMapper.toEmployeeQueryRequestDTO(request);

            var paginatedEmployees = employeeService.queryEmployees(requestContextDTO, queryRequestDTO);

            var response = employeeGrpcMapper.toPaginatedEmployeeResponse(paginatedEmployees);

            log.info("queryEmployees gRPC Controller Completed");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            errorLog("queryEmployees", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    @Override
    public void getAllEmployees(EmployeeProto.GetAllEmployeesRequest request, StreamObserver<EmployeeProto.PaginatedEmployeeResponse> responseObserver) {
        log.info("getAllEmployees gRPC Controller...");

        try {
            var requestContextDTO = RequestContextDTO.builder()
                    .correlationId(UUID.fromString(ContextKeys.CORRELATION_ID_CONTEXT_KEY.get()))
                    .consumerId(ContextKeys.CONSUMER_ID_CONTEXT_KEY.get())
                    .storeId(ContextKeys.STORE_ID_CONTEXT_KEY.get() != null ? UUID.fromString(ContextKeys.STORE_ID_CONTEXT_KEY.get()) : null)
                    .build();

            var pageable = PageableUtil.buildPageable(request.getPage(), request.getSize(), request.getSortList());
            var pagination = SearchParamsDTO.builder()
                    .pageable(pageable)
                    .build();

            var paginatedEmployees = employeeService.getAllEmployees(requestContextDTO, pagination);

            var response = employeeGrpcMapper.toPaginatedEmployeeResponse(paginatedEmployees);

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            log.info("getAllEmployees gRPC Controller complete.");
        } catch (Exception e) {
            errorLog("getAllEmployees", e);
            grpcException.grpcException(e, responseObserver);
        }
    }

    private void errorLog(String method, Exception e) {
        log.error("Method : {} Error: {}", method, e.getMessage());
    }
}
