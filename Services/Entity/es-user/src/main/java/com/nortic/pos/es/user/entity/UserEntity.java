
package com.nortic.pos.es.user.entity;

import com.nortic.pos.es.user.dto.enums.DocumentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "\"user\"",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_document_number",
                        columnNames = "document_number"
                ),
                @UniqueConstraint(
                        name = "uk_user_username",
                        columnNames = "username"
                ),
                @UniqueConstraint(
                        name = "uk_user_email",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "uk_user_phone",
                        columnNames = "phone"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(
        sql = """
        UPDATE "user"
        SET deleted_at = CURRENT_TIMESTAMP,
            is_active = FALSE
        WHERE id = ?
        """
)
@SQLRestriction("deleted_at IS NULL")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private UUID id;

    @Column(
            name = "first_name",
            length = 100,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 100,
            nullable = false
    )
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "document_type",
            length = 50,
            nullable = false
    )
    private DocumentTypeEnum documentType;

    @Column(
            name = "document_number",
            length = 100,
            nullable = false
    )
    private String documentNumber;

    @Column(
            name = "username",
            length = 100,
            nullable = false
    )
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "phone",
            length = 50
    )
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dateOfBirth;

    @Column(
            name = "photo",
            columnDefinition = "TEXT"
    )
    private String photo;

    @Builder.Default
    @Column(
            name = "is_active",
            nullable = false
    )
    private Boolean isActive = Boolean.FALSE;

    @CreationTimestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime updatedAt;

    @Column(
            name = "deleted_at",
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private OffsetDateTime deletedAt;
}
