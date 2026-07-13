package com.nortic.pos.es.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private UUID id;
    private UUID ownerId;
    private String taxId;
    private String name;
    private String description;
    private String slogan;
    private String logo;
    private String address;
    private String country;
    private String stateProvince;
    private String city;
    private String phoneNumber;
    private String email;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
}
