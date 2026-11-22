package com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.client;

import com.nortic.pos.es.employee.grpc.EmployeeServiceGrpc;
import com.nortic.pos.ms.auth.application.port.output.EmployeeClient;
import com.nortic.pos.ms.auth.domain.model.Employee;
import com.nortic.pos.ms.auth.domain.model.LoginCredentials;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.mapper.EmployeeGrpcMapper;
import com.nortic.pos.ms.auth.infrastructure.adapter.output.grpc.utils.GrpcHeaders;
import io.grpc.stub.MetadataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeGrpcClient implements EmployeeClient {

    private final GrpcHeaders grpcHeaders;
    private final EmployeeGrpcMapper employeeGrpcMapper;
    private final EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeStub;

    @Override
    public Employee authenticate(LoginCredentials loginCredentials) {
        log.info("EmployeeGrpcClient authenticate Start...");
        var credentials = employeeGrpcMapper.toAuthenticateRequest(loginCredentials);
        var employee = employeeStub.withInterceptors(
                MetadataUtils.newAttachHeadersInterceptor(grpcHeaders.generateHeaders())
        ).authenticate(credentials);
        log.info("EmployeeGrpcClient authenticate Finalized...");
        return employeeGrpcMapper.toEmployee(employee);
    }
}
