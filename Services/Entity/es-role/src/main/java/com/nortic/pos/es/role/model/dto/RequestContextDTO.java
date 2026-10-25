package com.nortic.pos.es.role.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestContextDTO {

    private UUID correlationId;

    private String consumerId;

}
