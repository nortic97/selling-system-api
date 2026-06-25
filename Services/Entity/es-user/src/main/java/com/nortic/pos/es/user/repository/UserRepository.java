package com.nortic.pos.es.user.repository;

import com.nortic.pos.es.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByDocumentTypeAndDocumentNumber(
            String documentType,
            String documentNumber
    );

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByDocumentTypeAndDocumentNumber(
            String documentType,
            String documentNumber
    );

}
