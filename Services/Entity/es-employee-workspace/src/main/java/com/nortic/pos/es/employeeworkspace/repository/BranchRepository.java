package com.nortic.pos.es.employeeworkspace.repository;

import com.nortic.pos.es.employeeworkspace.model.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
}
