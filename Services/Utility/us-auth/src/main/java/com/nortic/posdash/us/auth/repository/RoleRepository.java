package com.nortic.posdash.us.auth.repository;

import com.nortic.posdash.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    @Query(value = "SELECT role_has_permission(:roleId, :method, :url)", nativeQuery = true)
    boolean hasPermission(@Param("roleId") UUID roleId,
                          @Param("method") String method,
                          @Param("url") String url);
}
