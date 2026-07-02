package com.nortic.pos.es.user.repository;

import com.nortic.pos.es.user.dto.enums.DocumentTypeEnum;
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
    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneAndIdNot(String phone, UUID id);

    boolean existsByDocumentTypeIgnoreCaseAndDocumentNumber(
            DocumentTypeEnum documentType,
            String documentNumber
    );

    boolean existsByEmailIgnoreCaseAndIdNot(
            String email,
            UUID id
    );

    List<UserEntity> id(UUID id);

    @NativeQuery(
            """
                    SELECT * FROM "user" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<UserEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
