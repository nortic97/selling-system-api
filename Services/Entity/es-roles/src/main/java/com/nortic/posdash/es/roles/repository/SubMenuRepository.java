package com.nortic.posdash.es.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubMenuRepository extends JpaRepository<SubMenuEntity, UUID> {
}
