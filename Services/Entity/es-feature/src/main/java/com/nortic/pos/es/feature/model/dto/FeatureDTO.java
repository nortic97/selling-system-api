package com.nortic.pos.es.feature.model.dto;

import com.nortic.pos.es.feature.model.enums.FeatureTypeEnum;
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
public class FeatureDTO {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private FeatureTypeEnum type;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime deletedAt;

}
