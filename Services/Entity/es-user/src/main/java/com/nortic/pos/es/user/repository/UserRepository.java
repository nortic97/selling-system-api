package com.nortic.pos.es.user.repository;

import com.nortic.pos.es.user.dto.DocumentTypeEnum;
import com.nortic.pos.es.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByDocumentTypeAndDocumentNumber(
            DocumentTypeEnum documentType,
            String documentNumber
    );

    List<UserEntity> id(UUID id);

    @NativeQuery(
            """
                    SELECT * FROM "user" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<UserEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
