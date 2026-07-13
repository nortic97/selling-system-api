package com.nortic.pos.es.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestDTO {

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
}
