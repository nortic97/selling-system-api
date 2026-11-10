package com.nortic.pos.es.employeeworkspace.repository;

import com.nortic.pos.es.employeeworkspace.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
}
