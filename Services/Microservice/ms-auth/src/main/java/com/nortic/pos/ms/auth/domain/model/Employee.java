package com.nortic.pos.ms.auth.domain.model;

import com.nortic.pos.ms.auth.domain.model.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private UUID id;

    private UUID storeId;

    private String firstName;

    private String lastName;

    private DocumentTypeEnum documentType;

    private String documentNumber;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;

    private LocalDate dateOfBirth;

    private String photoUrl;

    private Boolean isActive;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;
}
