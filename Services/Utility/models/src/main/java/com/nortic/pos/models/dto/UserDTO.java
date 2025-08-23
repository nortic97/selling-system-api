package com.nortic.pos.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nortic.pos.models.enums.AccountTypeEnum;
import com.nortic.pos.models.enums.DocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private UUID id;
    private UUID storeId;
    private UUID branchId;
    private String firstName;
    private String lastName;
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private UUID roleId;
    private AccountTypeEnum accountType;
    private boolean isActive;
    private boolean isPrincipalAdmin;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String photo;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedAt;
}
