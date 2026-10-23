package com.nortic.pos.es.branch.model.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private UUID id;
    private UUID storeId;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
}
