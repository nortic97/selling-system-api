package com.nortic.pos.es.employee.service;

import com.nortic.pos.es.employee.exception.EmployeeBusinessException;
import com.nortic.pos.es.employee.exception.EmployeeExceptionCode;
import com.nortic.pos.es.employee.mapper.EmployeeEntityMapper;
import com.nortic.pos.es.employee.model.dto.AuthenticateRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeEmailRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeEmployeeStatusRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangePasswordRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangePhoneRequestDTO;
import com.nortic.pos.es.employee.model.dto.ChangeusernameRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeQueryRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeSearchRequestDTO;
import com.nortic.pos.es.employee.model.dto.PaginatedEmployeeResponseDTO;
import com.nortic.pos.es.employee.model.dto.RequestContextDTO;
import com.nortic.pos.es.employee.model.dto.SearchParamsDTO;
import com.nortic.pos.es.employee.repository.EmployeeRepository;
import com.nortic.pos.es.employee.service.contract.EmployeeService;
import com.nortic.pos.es.employee.service.contract.EmployeeSpecificationService;
import com.nortic.pos.es.employee.service.contract.EmployeeValidationService;
import com.nortic.pos.es.employee.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmployeeEntityMapper employeeEntityMapper;

    private final EmployeeValidationService employeeValidationService;

    private final EmployeeSpecificationService employeeSpecificationService;

    @Override
    @Transactional
    public EmployeeDTO changeEmail(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeEmailRequestDTO changeEmailRequestDTO
    ) {
        log.info("Changing email service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        employeeValidationService.validateEmail(id, changeEmailRequestDTO.getEmail());

        employee.setEmail(changeEmailRequestDTO.getEmail().trim().toLowerCase());

        log.info("Changing email service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO changePhone(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangePhoneRequestDTO changePhoneRequestDTO
    ) {
        log.info("Changing phone service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        employeeValidationService.validatePhone(id, changePhoneRequestDTO.getPhone());

        employee.setPhone(changePhoneRequestDTO.getPhone());

        log.info("Changing phone service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO changeEmployeePassword(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangePasswordRequestDTO changePasswordRequestDTO
    ) {
        log.info("Changing password service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        employeeValidationService.validatePasswordChange(
                changePasswordRequestDTO.getCurrentPassword(),
                changePasswordRequestDTO.getNewPassword(),
                employee.getPassword()
        );

        employee.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));

        log.info("Changing password service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO changeEmployeeStatus(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeEmployeeStatusRequestDTO changeStatusRequestDTO
    ) {
        log.info("Changing employee status service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        employee.setIsActive(changeStatusRequestDTO.getActive());

        log.info("Changing employee status service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeDTO changeusername(
            RequestContextDTO requestContextDTO,
            UUID id,
            ChangeusernameRequestDTO changeusernameRequestDTO
    ) {
        log.info("Changing username service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        employeeValidationService.validateusername(id, changeusernameRequestDTO.getusername());

        employee.setusername(changeusernameRequestDTO.getusername().trim().toLowerCase());

        log.info("Changing username service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO authenticate(
            RequestContextDTO requestContextDTO,
            AuthenticateRequestDTO authenticateRequestDTO
    ) {
        log.info("Authenticating employee service...");

        String normalizedusername = authenticateRequestDTO.getusername().trim().toLowerCase();

        var employee = employeeRepository.findByusernameIgnoreCase(normalizedusername)
                .orElseThrow(() -> new EmployeeBusinessException(EmployeeExceptionCode.EMPLOYEE_NOT_FOUND));

        if (!passwordEncoder.matches(authenticateRequestDTO.getPassword(), employee.getPassword())) {
            throw new EmployeeBusinessException(EmployeeExceptionCode.INVALID_PASSWORD);
        }

        log.info("Authentication service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO createEmployee(RequestContextDTO requestContextDTO, EmployeeDTO employeeDTO) {
        log.info("Creating employee service...");

        employeeValidationService.validateNewEmployee(employeeDTO);

        var employee = employeeEntityMapper.toEmployeeEntity(employeeDTO);

        employee.setIsActive(Boolean.FALSE);
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));

        var savedEmployee = employeeRepository.save(employee);

        log.info("Employee created whit id {}", savedEmployee.getId());

        return employeeEntityMapper.toEmployeeDTO(savedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Deleting employee service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);
        employeeRepository.delete(employee);

        log.info("Employee deleted whit id {}", id);
    }

    @Override
    public PaginatedEmployeeResponseDTO getAllEmployees(RequestContextDTO requestContextDTO, SearchParamsDTO searchParamsDTO) {
        log.info("Getting employee service...");

        var employees = employeeRepository.findAll(searchParamsDTO.getPageable());

        log.info("Getting employee service complete.");

        return employeeEntityMapper.toPaginatedEmployeeDTO(employees);
    }

    @Override
    public EmployeeDTO getEmployeeById(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Getting employee by id service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        log.info("Getting employee by id service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    public PaginatedEmployeeResponseDTO queryEmployees(RequestContextDTO requestContextDTO, EmployeeQueryRequestDTO employeeQueryRequestDTO) {
        log.info("Querying employees service...");

        var pageable = PageableUtil.buildPageable(
                employeeQueryRequestDTO.getPage(),
                employeeQueryRequestDTO.getSize(),
                employeeQueryRequestDTO.getSort()
        );

        var specification = employeeSpecificationService.buildQuerySpecification(employeeQueryRequestDTO);

        var employees = employeeRepository.findAll(specification, pageable);

        log.info("Querying employees service complete.");

        return employeeEntityMapper.toPaginatedEmployeeDTO(employees);
    }

    @Override
    @Transactional
    public EmployeeDTO restoreEmployee(RequestContextDTO requestContextDTO, UUID id) {
        log.info("Restoring employee service...");

        var employee = employeeRepository.findByIdIncludingDeleted(id).orElseThrow(() ->
                new EmployeeBusinessException(EmployeeExceptionCode.EMPLOYEE_NOT_FOUND));

        employee.setIsActive(Boolean.TRUE);
        employee.setDeletedAt(null);

        log.info("Restoring employee service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

    @Override
    public PaginatedEmployeeResponseDTO searchEmployees(RequestContextDTO requestContextDTO, EmployeeSearchRequestDTO employeeSearchRequestDTO) {
        log.info("Searching employees service...");

        var pageable = PageableUtil.buildPageable(
                employeeSearchRequestDTO.getPage(),
                employeeSearchRequestDTO.getSize(),
                employeeSearchRequestDTO.getSort()
        );

        var specification = employeeSpecificationService.buildSearchSpecification(employeeSearchRequestDTO);

        var employees = employeeRepository.findAll(specification, pageable);

        log.info("Searching employees service complete.");

        return employeeEntityMapper.toPaginatedEmployeeDTO(employees);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(
            RequestContextDTO requestContextDTO,
            UUID id,
            EmployeeDTO employeeDTO
    ) {
        log.info("Updating employee service...");

        var employee = employeeValidationService.validateAndGetEmployee(id);

        employeeEntityMapper.updateEmployeeFromDTO(employeeDTO, employee);

        log.info("Updating employee service complete.");

        return employeeEntityMapper.toEmployeeDTO(employee);
    }

}
