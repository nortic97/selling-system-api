package com.nortic.pos.es.employee.service.contract;

import com.nortic.pos.es.employee.model.dto.EmployeeQueryRequestDTO;
import com.nortic.pos.es.employee.model.dto.EmployeeSearchRequestDTO;
import com.nortic.pos.es.employee.model.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

public interface EmployeeSpecificationService {

    Specification<EmployeeEntity> buildSearchSpecification(EmployeeSearchRequestDTO searchRequest);

    Specification<EmployeeEntity> buildQuerySpecification(EmployeeQueryRequestDTO queryRequest);

}
