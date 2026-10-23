package com.nortic.pos.es.user.repository;

import com.nortic.pos.es.user.model.entity.UserEntity;
import com.nortic.pos.es.user.model.enums.DocumentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByPhoneIgnoreCaseAndIdNot(String phone, UUID id);

    boolean existsByPhoneAndIdNot(String phone, UUID id);

    boolean existsByDocumentTypeAndDocumentNumber(
            DocumentTypeEnum documentType,
            String documentNumber
    );

    boolean existsByEmailIgnoreCaseAndIdNot(
            String email,
            UUID id
    );

    boolean existsByUsernameIgnoreCaseAndIdNot(String username, UUID id);

    List<UserEntity> id(UUID id);

    @NativeQuery(
            """
                    SELECT * FROM "user" WHERE id = :id AND deleted_at IS NOT NULL AND is_active IS FALSE;
                    """
    )
    Optional<UserEntity> findByIdIncludingDeleted(@Param("id") UUID id);
}
