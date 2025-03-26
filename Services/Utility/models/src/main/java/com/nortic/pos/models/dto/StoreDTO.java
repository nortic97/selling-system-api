package com.nortic.pos.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    UUID id;
    String taxId;
    String name;
    String logo;
    String description;
    String slogan;
    String address;
    String stateProvince;
    String city;
    String phoneNumber;
    String email;
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;
}
