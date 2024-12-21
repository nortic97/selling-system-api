package com.nortic.posdash.es.users.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nortic.posdash.models.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserName(String username);
    Page<UserEntity> findAllByDeletedAtIsNull(Pageable pageable);
    List<UserEntity> findAllByDeletedAtIsNull(Sort sort);
    Page<UserEntity> findAllByDeletedAtIsNotNull(Pageable pageable);
}
