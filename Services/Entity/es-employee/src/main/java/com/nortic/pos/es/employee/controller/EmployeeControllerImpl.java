package com.nortic.pos.es.employee.controller;

import com.nortic.pos.es.employee.mapper.EmployeeRestMapper;
import com.nortic.pos.es.employee.model.dto.RequestContextDTO;
import com.nortic.pos.es.employee.model.dto.SearchParamsDTO;
import com.nortic.pos.es.employee.rest.gen.api.EmployeeApi;
import com.nortic.pos.es.employee.rest.gen.model.*;
import com.nortic.pos.es.employee.service.contract.EmployeeService;
import com.nortic.pos.es.employee.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeApi {

    private final EmployeeRestMapper employeeRestMapper;

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> changeEmail(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangeEmailRequest restModelChangeEmailRequest
    ) {
        log.info("Changing email Controller complete.");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeEmailRequestDTO = employeeRestMapper.toChangeEmailRequestDTO(restModelChangeEmailRequest);

        var changedEmployee = employeeService.changeEmail(requestContextDTO, id, changeEmailRequestDTO);

        log.info("Changing email Controller complete.");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(changedEmployee));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> changePhone(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangePhoneRequest restModelChangePhoneRequest
    ) {
        log.info("Changing phone Controller complete.");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changePhoneRequestDTO = employeeRestMapper.toChangePhoneRequestDTO(restModelChangePhoneRequest);

        var changedEmployee = employeeService.changePhone(requestContextDTO, id, changePhoneRequestDTO);

        log.info("Changing phone Controller complete.");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(changedEmployee));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> changeEmployeePassword(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangePasswordRequest restModelChangePasswordRequest
    ) {
        log.info("Changing employee password REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changePasswordRequestDTO = employeeRestMapper.toChangePasswordRequestDTO(restModelChangePasswordRequest);

        var changedEmployee = employeeService.changeEmployeePassword(requestContextDTO, id, changePasswordRequestDTO);

        log.info("Changing employee password REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(changedEmployee));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> changeEmployeeStatus(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangeEmployeeStatusRequest restModelChangeEmployeeStatusRequest
    ) {
        log.info("Changing employee status REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeEmployeeStatusRequestDTO = employeeRestMapper.toChangeEmployeeStatusRequestDTO(restModelChangeEmployeeStatusRequest);

        var changedEmployee = employeeService.changeEmployeeStatus(requestContextDTO, id, changeEmployeeStatusRequestDTO);

        log.info("Changing employee status REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(changedEmployee));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> changeUsername(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelChangeUsernameRequest restModelChangeUsernameRequest
    ) {
        log.info("Changing username REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var changeUsernameRequestDTO = employeeRestMapper.toChangeUsernameRequestDTO(restModelChangeUsernameRequest);

        var changedEmployee = employeeService.changeUsername(requestContextDTO, id, changeUsernameRequestDTO);

        log.info("Changing username REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(changedEmployee));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> authenticate(
            UUID correlationId,
            String consumerId,
            RestModelAuthenticateRequest restModelAuthenticateRequest
    ) {
        log.info("Authenticating employee REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var authenticateRequestDTO = employeeRestMapper.toAuthenticateRequestDTO(restModelAuthenticateRequest);

        var employee = employeeService.authenticate(requestContextDTO, authenticateRequestDTO);

        log.info("Authenticating employee REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(employee));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> createEmployee(
            UUID correlationId,
            String consumerId,
            RestModelCreateEmployeeRequestDTO restModelCreateEmployeeRequestDTO
    ) {
        log.info("Creating employee REST controller...");

        var employee = employeeRestMapper.toEmployeeDTO(restModelCreateEmployeeRequestDTO);

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var createdEmployee = employeeService.createEmployee(requestContextDTO, employee);

        log.info("Creating employee REST Controller finished...");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeRestMapper.toRestModelEmployeeResponseDTO(createdEmployee));

    }

    @Override
    public ResponseEntity<Void> deleteEmployee(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {

        log.info("Deleting employee REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        employeeService.deleteEmployee(requestContextDTO, id);

        log.info("Deleting employee REST controller finished...");

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RestModelPaginatedEmployeeResponseDTO> getAllEmployees(
            UUID correlationId,
            String consumerId,
            UUID storeId,
            Integer page,
            Integer size,
            @Nullable List<String> sort
    ) {
        log.info("Getting all employees REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .storeId(storeId)
                .build();

        var pagination = SearchParamsDTO.builder()
                .storeId(storeId)
                .pageable(PageableUtil.buildPageable(page, size, sort))
                .build();

        var paginatedEmployees = employeeService.getAllEmployees(requestContextDTO, pagination);

        log.info("Getting all employees REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelPaginatedEmployeeResponseDTO(
                paginatedEmployees));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> getEmployeeById(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {
        log.info("Getting employee by id REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var employee = employeeService.getEmployeeById(requestContextDTO, id);

        log.info("Getting employee by id REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(employee));
    }

    @Override
    public ResponseEntity<RestModelPaginatedEmployeeResponseDTO> queryEmployees(
            UUID correlationId,
            String consumerId,
            @Nullable RestModelEmployeeQueryRequestDTO restModelEmployeeQueryRequestDTO
    ) {
        log.info("Querying employees REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var employeeQueryRequestDTO = employeeRestMapper.toEmployeeQueryRequestDTO(restModelEmployeeQueryRequestDTO);

        var paginatedEmployees = employeeService.queryEmployees(requestContextDTO, employeeQueryRequestDTO);

        log.info("Querying employees REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelPaginatedEmployeeResponseDTO(paginatedEmployees));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> restoreEmployee(
            UUID correlationId,
            UUID id,
            String consumerId
    ) {
        log.info("Restoring employee REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var employee = employeeService.restoreEmployee(requestContextDTO, id);

        log.info("Restoring employee REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(employee));
    }

    @Override
    public ResponseEntity<RestModelPaginatedEmployeeResponseDTO> searchEmployees(
            UUID correlationId,
            String consumerId,
            @Nullable RestModelEmployeeSearchRequestDTO restModelEmployeeSearchRequestDTO
    ) {
        log.info("Searching employees REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var employeeSearchRequestDTO = employeeRestMapper.toEmployeeSearchRequestDTO(restModelEmployeeSearchRequestDTO);

        var paginatedEmployees = employeeService.searchEmployees(requestContextDTO, employeeSearchRequestDTO);

        log.info("Searching employees REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelPaginatedEmployeeResponseDTO(paginatedEmployees));
    }

    @Override
    public ResponseEntity<RestModelEmployeeResponseDTO> updateEmployee(
            UUID correlationId,
            UUID id,
            String consumerId,
            RestModelUpdateEmployeeRequestDTO restModelUpdateEmployeeRequestDTO
    ) {
        log.info("Updating employee REST controller...");

        var requestContextDTO = RequestContextDTO.builder()
                .correlationId(correlationId)
                .consumerId(consumerId)
                .build();

        var employee = employeeRestMapper.toEmployeeDTO(restModelUpdateEmployeeRequestDTO);

        var updatedEmployee = employeeService.updateEmployee(requestContextDTO, id, employee);

        log.info("Updating employee REST controller finished...");

        return ResponseEntity.ok(employeeRestMapper.toRestModelEmployeeResponseDTO(updatedEmployee));
    }

}
