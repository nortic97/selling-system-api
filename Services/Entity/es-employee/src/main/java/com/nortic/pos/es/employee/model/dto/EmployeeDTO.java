package com.nortic.pos.es.employee.model.dto;

import com.nortic.pos.es.employee.model.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private UUID id;

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
