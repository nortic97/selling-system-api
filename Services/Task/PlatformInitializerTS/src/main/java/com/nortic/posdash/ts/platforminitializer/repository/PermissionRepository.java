package com.nortic.posdash.ts.platforminitializer.repository;

import com.nortic.posdash.models.entity.PermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionsEntity, Long> {
    boolean existsByUrlAndMethod(String url, String method);
}
