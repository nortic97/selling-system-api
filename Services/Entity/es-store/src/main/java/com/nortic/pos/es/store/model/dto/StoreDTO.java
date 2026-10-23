package com.nortic.pos.es.store.model.dto;

import com.nortic.pos.es.store.model.enums.CurrencyCodeEnum;
import com.nortic.pos.es.store.model.enums.TaxTypeEnum;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private UUID id;
    private UUID ownerId;
    private TaxTypeEnum taxType;
    private String taxId;
    private String legalName;
    private String name;
    private String logoUrl;
    private String description;
    private String slogan;
    private String address;
    private String country;
    private String stateProvince;
    private String city;
    private String phoneNumber;
    private String contactEmail;
    private CurrencyCodeEnum currencyCode;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;
}
