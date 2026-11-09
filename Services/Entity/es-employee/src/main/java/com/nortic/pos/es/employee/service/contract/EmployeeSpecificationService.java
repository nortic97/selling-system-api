package com.nortic.pos.es.employee.service.contract;

import com.nortic.pos.es.employee.model.dto.EmployeeQueryRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeSearchRequestDTO;
import com.nortic.pos.es.employee.model.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface EmployeeSpecificationService {

    Specification<EmployeeEntity> buildSearchSpecification(EmployeeSearchRequestDTO searchRequest, UUID storeId);

    Specification<EmployeeEntity> buildQuerySpecification(EmployeeQueryRequestDTO queryRequest, UUID storeId);

}
